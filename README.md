
# Android 开发基于百度语音识别技术的小程序



[百度开发者平台](http://developer.baidu.com)为开发者提供了很多工具，虽然我对百度无感，但是因为有了这些工具，使我们开发程序更加快捷、便利。本文将会简单介绍如何使用[百度语音识别技术](http://yuyin.baidu.com/)丰富我们自己的程序。



###背景介绍
随着大家对Siri 的认知，语音识别技术被应用的越来越广泛。对于一个普通开发者而言，是没有精力去开发一个语音识别的模块的，所以利用现有的技术来实现是最好的选择。
在下面，我们将会介绍如将百度提供的语音识别技术应用到我们自己的项目中。



###准备工作
首先需要在百度的网站上注册成为一个名开发者。无论是用使用微信提供的接口，还是百度提供的，首先都去官网注册成为一名开发者。

然后新建自己的应用，并且给自己的应用添加语音识别的功能，这个时候百度会按照你的ID给你生成两个Key，一个是API Key，一个是Secret Key，这两个Key 是需要用在你自己的应用程序中的。

![这里写图片描述](http://img.blog.csdn.net/20150508134028437)

然后去[下载页面](http://yuyin.baidu.com/asr)去下载响应的Android SDK 和一些文档。


###利用SDK 进行开发

首先新建一个项目，然后将开发包中的 Libs 目录整体 Copy 到工程目录， Libs 目录包括了各平台的 SO 库， 开发者视应用需要可以进行删减。

然后在工程中添加权限：
	
	
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />


因为百度将语音识别技术集成在BaiduASRDigitalDialog 中，然后通过调用这个Dialog 来做处理。并且通过DialogRecognitionListener 来做消息的响应。所以我们需要新建这两个对象：
	
    private BaiduASRDigitalDialog mDialog = null;
	private DialogRecognitionListener mRecognitionListener;



我的程序是通过点击一个按钮后，调出语音识别对话框的，代码如下：
	
	public void ClickButton(View v){
    	//Toast.makeText(getApplicationContext(), "测试",  Toast.LENGTH_SHORT).show();
        mCurrentTheme = Config.DIALOG_THEME;
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Bundle params = new Bundle();
        params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, 	       "QRrYgx7bcO11NWUFFG61jtBN");
        params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, "iRNUTBM9Tli0px1HqqyYLtdMdy95ChGz");
        params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, Config.DIALOG_THEME);
        mDialog = new BaiduASRDigitalDialog(this, params);
        mDialog.setDialogRecognitionListener(mRecognitionListener);
        mDialog.show();
    }


当进行完识别后，还需要对识别后的结果作出响应。可以看到上面的代码有一个setDialogRecognitionListener这个函数来做对响应的监听。

下面是我首先的相应监听函数：

	mRecognitionListener = new DialogRecognitionListener() {
    	@Override
		public void onResults(Bundle results) {
          ArrayList<String> rs = results != null ? results
                        .getStringArrayList(RESULTS_RECOGNITION) : null;
                if (rs != null && rs.size() > 0) {
                   //setBackgroundColor(rs.get(0));
                    setPicture(rs.get(0));
                }

            }
        };


当做完对语音的识别后，百度服务器会返回一个对应的字符串组rs，取出rs的第一个词，来作为结果。

百度对这个语音识别Dialog 分装的很好，非常利于开发者使用，过程就是：
调出BaiduASRDigitalDialog 对象，该对象负责了录音已经上传到服务器中去做处理，得到的结果在DialogRecognitionListener 中予以响应。


###我的例子
我做了个简单的例子，你说“苹果”、“梨”、“草莓”、“香蕉”后会将ImageView 设置成对应的图片。首先打开程序后是图一：  
![这里写图片描述](http://img.blog.csdn.net/20150508134314986)

点击按钮后，弹出对话框，说“香蕉”，画面会改变为香蕉的图片。  
![这里写图片描述](http://img.blog.csdn.net/20150508135232856)



