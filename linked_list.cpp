// Online C++ compiler to run C++ program online
#include <iostream>
#include<bits/stdc++.h>
#include <cstdio>

using namespace std;



int vis[20015], col[20015],mn,mx,rd,gn;
vector<int> adj[20015];

void dfs(ll u) {
    vis[u] = true;
    if(col[u] == 1) rd++;
    else gn++;

    for (ll v : adj[u]) {
        if (!vis[v]) {
            if(col[u] == 1) col[v]=2;
            else col[v]=1;
            dfs(v);
        }
    }
}

int main() {
    int tc=1, mtc;
    // cin >> tc;
    while(tc--){
        memset(vis, 0, sizeof(vis));
        memset(col, 0, sizeof(col));
        mn=300000;
        mx=-1;
        rd=0;
        gn=0;
        for(int i=0;i<20015;i++)  adj[i].clear();
        cin >> mtc;
        while(mtc--){
            int u,v;
            cin >> u >> v;
            adj[u].push_back(v);
            adj[v].push_back(u);
            int xxx=min(u,v);
            mn=min(xxx,mn);
            int yyy=max(u,v);
            mx=max(mx,yyy);
        }
        
        for(int i=mn;i<=mx;i++){
            if(vis[i]) continue;
            vis[i]=1;
            col[i]=1;
            dfs(i);
        }
        
        int ans=max(rd,gn);
        cout << ans << endl;
        
    }

    return 0;
}