package javaapplication30;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Queuing implements ActionListener {
    JFrame frame =new JFrame();
    JTextArea simulationOutput=new JTextArea();
    Border b = BorderFactory.createLineBorder(Color.BLACK, 2);   
    Border b2 = BorderFactory.createLineBorder(Color.white, 2);   
    JLabel l;
    double[] a={};
    JTextField numSimulation;
    Font f=new Font("MV Boli", Font.BOLD, 24);
    Container C =frame.getContentPane();
    JTextField[] tfs=new JTextField[10];
    JLabel[] labls=new JLabel[20];
      double  mu1, lm1;
    simulationTable j;    
    int c;
    int typeModel=0;
    double L,Lq,W,Wq,ro,Pn,r;
   
    int k;
    Color col=new Color(144,200,144);
   int numsim;
   Graph g =new Graph(a); 
   public Queuing(){
        DecimalFormat df=new DecimalFormat("#.####");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("QUEUING THEORY");
        frame.setFont(new Font("MV Boli", Font.ITALIC, 150));
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocation(100, 0);
        C.setBackground(new Color(144,144,0));
        int x=20,y=80,w=200,h=50;

// This loop to create 4 JTfield
        for (int i = 0; i < 4; i++) {
            tfs[i]=new JTextField();
            tfs[i].setFont(f);
            tfs[i].setHorizontalAlignment(JLabel.CENTER);
            tfs[i].setBackground(col);
            tfs[i].setBounds(9*x+10, y+i*60-60, w-10, h);
            C.add(tfs[i]);
        }
//This loop to create 20 JLabel        
        for (int i = 0; i < 13; i++) {
//create frist 4 label (server,cabcity,lamda,mu)
            if(i<4){
            labls[i]=new JLabel("dddddd");
            labls[i].setBounds(x, y+i*60-60, w, h);
            labls[i].setFont(f);
            C.add(labls[i]);
            }
//create 4 label (L,Lq,W,Wq)
            if(i>=4&&i<8){
            labls[i]=new JLabel("lllllll");
            labls[i].setBounds(x, y+i*60+120, w, h);
            labls[i].setFont(f);
            C.add(labls[i]);
            }

//create 4 label answer for frist (L,Lq,W,Wq)           
            if(i>=8&&i<12){
            labls[i]=new JLabel("");
            labls[i].setBounds(x+70, y+(i-8)*60+360, 150, h);
            labls[i].setFont(f);
            labls[i].setBorder(b2);
            labls[i].setBackground(new Color(25, 25, 25));
            labls[i].setForeground(new Color(25, 255, 0));
            labls[i].setFont(new Font("Ink Free", Font.ITALIC, 24));
            labls[i].setHorizontalAlignment(JLabel.CENTER);
            labls[i].setOpaque(true);
            C.add(labls[i]);
            }

        }
        l=new JLabel("num simulation:");
            l.setBounds(x-10, y+4*60-60, w, h);
            l.setFont(f);
            C.add(l);
            numSimulation=new JTextField();
            numSimulation.setFont(f);
            numSimulation.setHorizontalAlignment(JLabel.CENTER);
            numSimulation.setBackground(col);
            numSimulation.setBounds(9*x+30, y+4*60-60, w-30, h);
            C.add(numSimulation);
            
          JLabel   l2=new JLabel("Simulation Table");
            l2.setBounds(x*50-100, y-65, w+100, h);
            l2.setFont(new Font("MV Boli", Font.BOLD, 30));
            C.add(l2);
            
labls[0].setText("Server :");labls[1].setText("Cabacity :");labls[2].setText("Lamda :");labls[3].setText("Mu :");
labls[4].setText("L :");labls[5].setText("Lq :");labls[6].setText("W :");labls[7].setText("Wq :");
// Create JTextArea for simulation output with JScrollPane
      
        simulationOutput.setEditable(false);
        simulationOutput.setFont(new Font("Monospaced", Font.PLAIN, 16));
        simulationOutput.setBackground(Color.BLACK);
        simulationOutput.setForeground(Color.GREEN);
        simulationOutput.setLineWrap(true);
        simulationOutput.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(simulationOutput);
        scrollPane.setBounds(400, 70, 1100, 300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        C.add(scrollPane);
//create botton
        JButton b1=new JButton("S o l v e");
        b1.setBounds(x, y+270, w, h);
        b1.setFont(f);
        b1.setBorder(b);
        b1.setBackground(Color.LIGHT_GRAY);
        C.add(b1);
        
        g.setBounds(400, 380, 1100, 400);
        g.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        C.add(g);
        frame.setVisible(true);
        
        b1.addActionListener(new ActionListener() {
         
            @Override
            public void actionPerformed(ActionEvent e) {
           
double lm1=Double.parseDouble(tfs[2].getText());
double mu1=Double.parseDouble(tfs[3].getText());
numsim=Integer.parseInt(numSimulation.getText()); 
c =Integer.parseInt(tfs[0].getText());           
//M/M/1
if (Double.parseDouble(tfs[1].getText())==0&&Double.parseDouble(tfs[0].getText())==1){
            typeModel=1;
            L=lm1/(mu1-lm1);
            Lq=lm1*lm1/(mu1*(mu1-lm1));
            W=1/(mu1-lm1);
            Wq=lm1/(mu1*(mu1-lm1));
          
            }    
// M/M/1/k 
            k =Integer.parseInt(tfs[1].getText());
if (Double.parseDouble(tfs[1].getText())!=0&&Double.parseDouble(tfs[0].getText())==1){
            
            ro=lm1/mu1;
            k=Integer.parseInt(tfs[1].getText());
            if(ro==1){
            Pn=1/(1+k);
                L=k/2;
            }else{
                Pn=(Math.pow(lm1/mu1,k)*((1-(lm1/mu1))/(1-Math.pow(lm1/mu1,k+1))));
            L=ro*((1-(k+1)*Math.pow(ro,k)+k*Math.pow(ro,k+1))/((1-ro)*(1-Math.pow(ro,k+1))));
            }
            
            Lq=L-(lm1/mu1)*(1-Pn);
            W=L/(lm1*(1-Pn));
            Wq=W-(1/mu1);
           
            }
// M/M/C   
r=lm1/mu1;
ro=r/c;                        
if (Double.parseDouble(tfs[1].getText())==0&&Double.parseDouble(tfs[0].getText())>1){

//calculate the value of P0              
    double p0=0;
//when r/c < 1               
        if(ro<1){
                  double a=(c*Math.pow(r,c))/((factorial(c))*(c-r));//calculate the second partition in the law
                   double m=0;
//calculate the frist partition in the low            
                   for (int i = 0; i <c ; i++) {
                      
                      m+=Math.pow(r,i)/factorial(i);
                  }
                    p0=a+m;
              } 
//when r/c >= 1           
        else{
                    double a=((Math.pow(r,c))*((c*mu1)/((c*mu1)-lm1)))/(factorial(c));
                double m=0;
                    for (int i = 0; i <c ; i++) {
                      
                      m+=(Math.pow(r,i)/factorial(i));
                  
                    }
                p0=m+a;
                }
//clculate the value of L,Lq,W,Wq                
                Lq=((Math.pow(r,c)*lm1*mu1)/(factorial(c-1)*(Math.pow(c*mu1-lm1,2))))*Math.pow(p0,-1);
                L=Lq+r;
                W=(Lq/lm1)+(1/mu1);
                Wq=Lq/lm1;
            }
// M/M/C/K            
if (Double.parseDouble(tfs[1].getText())!=0&&Double.parseDouble(tfs[0].getText())>1){

//calculate the value of P0              
    double p0=0;
//when r/c != 1               
       if (ro != 1) {
            double m = 0;
            for (int i = 0; i < c; i++) {
                m += Math.pow(r, i) / factorial(i);
            }
            double a = (Math.pow(r, c) / factorial(c)) * ((1 - Math.pow(ro, k - c + 1)) / (1 - ro));
            p0 = 1 / (m + a);
        } else {
            double m = 0;
            for (int i = 0; i < c; i++) {
                m += Math.pow(r, i) / factorial(i);
            }
            double a = (Math.pow(r, c) / factorial(c)) * (k - c + 1);
            p0 = 1 / (m + a);
        }

        // حساب Pk
        double pk = calculatePn(r, k, k, c, p0);
   double sum = 0;
        for (int i = 0; i < c; i++) {
            sum += (c - i) * Math.pow(r, i) / factorial(i);}
        // حساب Lq, L, W, Wq
         Lq = (ro * Math.pow(r, c) * p0 / (factorial(c) * Math.pow(1 - ro, 2))) *
                (1 - Math.pow(ro, k - c + 1) - (1 - ro) * (k - c + 1) * Math.pow(ro, k - c));
         L = Lq + c - (p0 * sum);
        W = L / (lm1 * (1 - pk));
         Wq = Lq / (lm1 * (1 - pk));

            }
//Print the value of L,Lq,W,Wq in the screen  
labls[8].setText(""+df.format(L));
labls[9].setText(""+df.format(Lq));
labls[10].setText(""+df.format(W));
labls[11].setText(""+df.format(Wq));
             
           if(typeModel==1){
    
    simulationTable j=new simulationTable(lm1, mu1, numsim, simulationOutput,C);
j.simulate();
//j.displayAverages(simulationOutput);
j.displayResults(simulationOutput);
 g.updateData(j.getValus());
           }
}});}
public double calculatePn(double r, int n,int K, int C,double p0) {
         if (n >= 0 && n < C) {
            return (Math.pow(r, n) * p0) / factorial(n);
        } else if (n >= C && n <= K) {
            return (Math.pow(r, n) * p0) / (factorial(C) * Math.pow(C, n - C));
        } else {
            return 0;
        }
    }
public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("العدد يجب أن يكون موجباً");
        } else if (n == 0) {
            return 1; // مضروب الصفر يساوي 1
        } else {
            return n * factorial(n - 1); // تطبيق العودية
        
        }}
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }
    
}    

