//app.js
App({
  onLaunch: function () {
    var that = this; 

    //用户登陆, 记录用户和本次会话
    console.info("start... login...");
    wx.login({
      success: res => {
        var sessToken = wx.getStorageSync("token");
        wx.request({
          url: that.globalData.url + '/auth/code',
          data: { 
            'code': res.code, 
            'appid': that.globalData.appid 
          },
          header: {
            'X-Wx-Token': sessToken
          },
          success: (res) => { 
            console.log("login success, and res.data=" + JSON.stringify(res.data));

            //将会话token写入本地缓存
            that.globalData.token = res.data.token;
            wx.setStorageSync('token', res.data.token);
          }
        });

        this.getUserInfo(null);
      },

      fail: res => {
        console.log("login...error res=" + JSON.stringify(res));
      }
    });
  },

  //获取用户信息
  getUserInfo: function (cb) {
    var that = this 
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      wx.getUserInfo({
        success: function (res) {
          that.globalData.userInfo = res.userInfo
          typeof cb == "function" && cb(that.globalData.userInfo)

          console.log("getUserInfo...success res=" + JSON.stringify(res));
        },

        fail: function (res) {
          console.log("getUserInfo...error res=" + JSON.stringify(res));
        }
      })
    }
  },

  globalData: {
    mallName: '门口小店',
    appid: 'wx4d57d80f45aa27c7',
    token: '',
    url: 'http://localhost:3700',
    userInfo: null,
    version: "v1.0.1",
    shareProfile: '百款精品商品，总有一款适合您'
  }
})
