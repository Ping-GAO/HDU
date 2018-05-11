#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;
struct bone{
	int value;
	int volume;
}myb[1005];
int dp[1005][1005];
int main(){
	int num;
	cin>>num;
	while(num){
		int N, V;
		cin>>N>>V;
		for(int i=0;i<N;i++){
			cin>>myb[i].value;
		}
		for(int i=0;i<N;i++){
			cin>>myb[i].volume;
		}
		memset(dp, 0, sizeof(dp));
		for(int i=0;i<N;i++){
			for(int j=0;j<=V;j++){
				if(j<myb[i].volume){
					dp[i+1][j] = dp[i][j];
				}
				else{
					dp[i+1][j] = max(dp[i][j],dp[i][j-myb[i].volume]+myb[i].value);
				}
			}
		}	
		cout<<dp[N][V]<<endl;
		
		num--;
	}

	return 0;
}
