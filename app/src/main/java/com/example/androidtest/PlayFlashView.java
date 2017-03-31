package com.example.androidtest;

import java.util.Random;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class PlayFlashView extends View{
	private int picthNum = 0;   //音调高低
	private int index=0;  //用来设置每0.3S重新获取一组 随机数
	int[] nowIndex= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int[] lastIndex= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	final int COUNT=30;
	int columnWidth=0;
	 // 创建画笔  
    Paint p = new Paint();  
    Paint p2 = new Paint();
    /* 创建一个缓冲区 */  
    Bitmap  mSCBitmap = null;  
    DisplayMetrics dm;
    int width=0;
    int height=0;
    
    
	public PlayFlashView(Context context) {
		super(context);
		
		init();
		// TODO Auto-generated constructor stub
	}

	public PlayFlashView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init(){
		p.setColor(Color.WHITE);// 白色
		p2.setColor(Color.GRAY);//灰色
		p.setStyle(Paint.Style.FILL);//设置填满 
		p2.setStyle(Paint.Style.FILL);//设置填满 
		p.setAntiAlias(true);  
        p.setDither(true); 
        p2.setAntiAlias(true);  
        p2.setDither(true); 
		p.setAlpha(255);  //设置透明度
		p2.setAlpha(80);  //设置透明度
		picthNum=0;		
		dm=new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		width=dm.widthPixels;
		height=dm.heightPixels;
		columnWidth=(width-30)/COUNT-5;
		//System.out.println("columnWidth          "+columnWidth);
		initScreen();
	};
	
	
	
	private void initScreen(){
		Canvas canvas=new Canvas();
		mSCBitmap=Bitmap.createBitmap(width,height,Config.ARGB_8888);
		canvas.setBitmap(mSCBitmap);
		setIndexList();   //获取随机长度		
		int everyWidth=columnWidth+5;  //每个柱子的宽度
		int Yheight=height/2-height/12;   //设置柱子Y坐标的起始位置 
		if(picthNum>0){   //如果当前音调>0
			for(int i=0;i<COUNT;i++){				
				if(nowIndex[i]>0){   //话柱子和横线					
					canvas.drawRect(everyWidth*i+15, Yheight-nowIndex[i], 15+everyWidth*i+columnWidth, Yheight, p);
					canvas.drawRect(everyWidth*i+15, Yheight, everyWidth*i+columnWidth+15, Yheight+(nowIndex[i]*60)/100, p2);
					if(lastIndex[i]<nowIndex[i]){
						canvas.drawLine(everyWidth*i+15, Yheight-nowIndex[i]-5, everyWidth*i+columnWidth+15, Yheight-nowIndex[i]-5, p);
						lastIndex[i]=nowIndex[i];
					}else if(lastIndex[i]-nowIndex[i]>7){
						lastIndex[i]=lastIndex[i]-2;
						canvas.drawLine(everyWidth*i+15, Yheight-lastIndex[i]-5, everyWidth*i+columnWidth+15, Yheight-lastIndex[i]-5, p);						
					}
				}else if(nowIndex[i]<=0){
					if(lastIndex[i]>2){
					    lastIndex[i]=lastIndex[i]-2;
						canvas.drawLine(everyWidth*i+15, Yheight-lastIndex[i], everyWidth*i+columnWidth+15, Yheight-lastIndex[i], p);						
					}else{
						lastIndex[i]=0;
						canvas.drawLine(everyWidth*i+15, Yheight, everyWidth*i+columnWidth+15, Yheight, p);						
					}
				  }						
			   }
			}else if(picthNum<=0){    //如果音调小于=0,则停止画柱子,只画横线		   
			   for(int i=0;i<COUNT;i++){
				   if(lastIndex[i]>2){
					    lastIndex[i]=lastIndex[i]-2;
						canvas.drawLine(everyWidth*i+15, Yheight-lastIndex[i], everyWidth*i+columnWidth+15, Yheight-lastIndex[i], p);						
					}else{
						lastIndex[i]=0;
						canvas.drawLine(everyWidth*i+15, Yheight, everyWidth*i+columnWidth+15, Yheight, p);						
					}
			  }			
		 }
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("king", "draw");
		super.onDraw(canvas);
		initScreen();
		canvas.drawBitmap(mSCBitmap, 0, 0, new Paint());
		
	}
	
	
	private void setIndexList(){
		index++;
		if(index>=2){
			index=0;
			Random r=new Random();
			for(int i=0;i<COUNT;i++){
				int num=picthNum+r.nextInt(100)-50;
				if(num>0){
					nowIndex[i]=num;
				}else{
					nowIndex[i]=0;
				}
			}				
		}
		
   }
	
	
	public void  updatePicthNum(int picthNum){
		this.picthNum=picthNum;
		new Thread(new puThread()).start();
	}
	
	class puThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(300);
				if(picthNum>1){
					picthNum=picthNum-1;
					
				}else{
					picthNum=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

    
    
}
