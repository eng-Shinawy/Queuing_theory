package javaapplication30;
import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {

    double arr[];

    public Graph (double a[]){arr=a;}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(Color.black);
        g.drawLine(10,0,10,arr.length*50);
        g.drawLine(0,390,arr.length*50,390);
        g.setColor(new Color(215,215, 215));

        //vertical lines
        for (int i=60;i<arr.length*50;i+=50) {
            
            g.drawLine(i,0,i,arr.length*50);
        }
        //Horezintal Line
        for (int i=arr.length*50;i>0;i-=50){
            g.drawLine(0,i,arr.length*50,i);
        }

        g.setColor(Color.black);

        for (int i=0;i<arr.length;i++){
            int num=i+1;
           g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);

            g.drawLine((int)(i*50+10),(int)(390-arr[i]*50),(int)((num)*50+10),(int)(390-arr[i]*50));
            //g.drawLine(i*50+10,455,i*50+10,455-arr[i]);
            //g.drawLine((num)*50+10,455,(num)*50+10,455-arr[i]);
        }

        for (int i=0;i<arr.length-1;i++){
            int shi=i*50+60;
         g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);

            g.drawLine(shi,(int)(390-arr[i]*50),shi,(int)(390-arr[i+1]*50));
        }
    }
public void updateData(double[] newArr) {
        arr = newArr;
        repaint(); // إعادة رسم الرسم البياني
    }

}

