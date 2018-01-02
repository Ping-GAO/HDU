#include <iostream>
#include <algorithm>
#include <cstring>
struct node{
	int val;
	int dp;
	int start;
	int end;
};
node array[100000];
using namespace std;
int find_max(int len);
int global_max;
int main(){
	int num;
	cin>>num;
	int i, j;
	for(i = 1; i <= num; i++){
		int len;
		cin>>len;
		//	memset(array,0,sizeof(array));
		for(j = 0; j < len; j++){
			cin>>array[j].val;
			array[j].dp = -100;
		}
		array[0].dp = array[0].val;
		array[0].start = array[0].end = 1;
		global_max = array[0].val;
		find_max(len-1);
		cout<<"Case "<<i<<":"<<endl;
		for(j = 0; j < len; j++){
			if(array[j].dp==global_max){
				cout<<array[j].dp<<" "<<array[j].start<<" "<<array[j].end<<endl;
				break;
			}
		}
		if(i!=num){	
			cout<<endl;
		}
	}


	return 0;
}



int find_max(int len){
	
	if(array[len].dp!=-100){
		// cout<<len<<array[len].dp<<endl;
		return array[len].dp;
	}
	
	
	if(len==0){
		return array[0].dp;
	}
	
	// array[len].dp =  max(array[len],find_max(len-1)+array[len]);
	if(array[len].val > find_max(len-1)+array[len].val){
		array[len].start = array[len].end = len + 1;
		array[len].dp = array[len].val;
	}
	else{
		array[len].start = array[len-1].start;
		array[len].end = len + 1;
		array[len].dp = find_max(len-1) + array[len].val;
	}
	if(array[len].dp>global_max){
		global_max = array[len].dp;
	}
	return array[len].dp;
}
