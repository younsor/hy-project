//login.js
//获取应用实例
var app = getApp();
Page({
  data: {
    pageStatus: '加载中',
    userInfo: {}
  },

  goIntoMall:function(){
    wx.switchTab({
      url: '/pages/index/index',
    });
  },

  onLoad:function(){
    var that = this
    wx.setNavigationBarTitle({
      title: app.globalData.mallName
    })

    app.getUserInfo(function(userInfo){
      that.setData({
        userInfo: userInfo
      })
    })
  },

  onShow:function(){

  },
 
  onReady: function(){
    var that = this;
    setTimeout(function(){
      that.setData({
        pageStatus: ''
      });
    }, 300);
  }
});