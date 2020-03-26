import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Leetcode 10. Regular Expression Matching
class Solution {


    private List<String> patternList;
    private String s;
    private String p;

    public boolean isMatch(String s, String p) {
        // loop each char in the pattern
        this.s = s;
        this.p = p;
        patternList = new ArrayList<>();
        preProcess();
        System.out.println(patternList.toString());
        return parsePattern(0, 0);

    }

    public void preProcess() {
        Stack<Character> stackOfPattern = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            stackOfPattern.push(p.charAt(i));
        }
        boolean twoCharPattern = false;
        while (!stackOfPattern.isEmpty()) {
            char currentPattern = stackOfPattern.pop();
            if (twoCharPattern) {
                patternList.add(currentPattern + "*");
                twoCharPattern = false;
            } else {
                if (currentPattern == '*') {
                    twoCharPattern = true;
                } else {
                    patternList.add(Character.toString(currentPattern));
                }
            }
        }
        Collections.reverse(patternList);
    }

    public boolean parsePattern(int currentPosInS, int currentPosInP) {
        if (currentPosInS == s.length() && currentPosInP == patternList.size()) {
            // the final match will match the last one and exceed the length
            return true;
        }
        if (currentPosInS == s.length()) {
            return restOfPatternIsNull(currentPosInP);
        }
        if (currentPosInP == patternList.size()) {
            return false;
        }
        int currentPatternLen = patternList.get(currentPosInP).length();
        char currentChar = s.charAt(currentPosInS);
        String currentPattern = patternList.get(currentPosInP);
        char currOneCharP = currentPattern.charAt(0);
        boolean result;
        if (currentPatternLen == 1) {
            // match '.' or a-z
            if (currOneCharP == '.') {
                result = parsePattern(currentPosInS + 1, currentPosInP + 1);
            } else {
                if (currentChar != currOneCharP) {
                    result = false;
                } else {
                    result = parsePattern(currentPosInS + 1, currentPosInP + 1);
                }
            }

        } else {
            // parse two char pattern
            result = false;
            int maxRepetition = maxRepetition(currentPosInS, currOneCharP);
            for (int i = 0; i <= maxRepetition; i++) {
                result = result | parsePattern(currentPosInS + i, currentPosInP + 1);
            }

        }
        return result;
    }

    private int maxRepetition(int currentPosInS, char charCanBeRepeat) {
        int maxRepetition = 0;
        if (charCanBeRepeat != '.') {
            for (int i = currentPosInS; i < s.length(); i++) {
                if (s.charAt(i) == charCanBeRepeat) {
                    maxRepetition++;
                } else {
                    break;
                }
            }
        } else {
            maxRepetition = s.length() - currentPosInS;
        }
        return maxRepetition;
    }

    private boolean restOfPatternIsNull(int currentPosInP) {
        System.out.println(currentPosInP);
        for (int i = currentPosInP; i < patternList.size(); i++) {
            if (patternList.get(i).length() != 2) {
                return false;
            } else {
                char first = patternList.get(i).charAt(0);
                char second = patternList.get(i).charAt(1);
                if (!((first == '.') || (first >= 'a' && first <= 'z') && second == '*')) {
                    return false;
                }
            }
        }
        return true;
    }
}

