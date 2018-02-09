//login.js
//获取应用实例
var app = getApp();
Page({
  data: {
    pageStatus: '加载中',
    userInfo: {}
  },
  goToIndex:function(){
    wx.switchTab({
      url: '/pages/index/index',
    });
  },
  onLoad:function(){
    var that = this
    wx.setNavigationBarTitle({
      title: wx.getStorageSync('mallName')
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
        remind: ''
      });
    }, 1000);
  }
});