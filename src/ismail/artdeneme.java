
package ismail;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import java.util.Random;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.io.*;
/**
 *
 * @author ismail
 */

class x {
    public static int sinifi = 0;
    public static ArrayList<Integer> yasak = new ArrayList<Integer>(10);
}

class neural { 
    public static double net = 0;
    public static double sonuc = 0;   
}
class process {
    double net;
    double cikti;
}

public class artdeneme extends javax.swing.JFrame {

    /** Creates new form artdeneme */
    public artdeneme() {
        initComponents();
    }
    int arakatlen, ciktikatlen;
    //art degisken tanımlaması baslangıcı
    double[][] Gateileri;
    double[][] Gategeri;
    double[] y;
    double benzerlik;
    String[][] girdiler;
    int M, N;
    x[] girdi;
    int index = 0;
    //art bitisi
    
    //adaline degisken tanımlama baslangıcı
    // int M, N, index = 0;
    double[] Gate;
    double[] bias;
    double alfa = 0.5;
   // String[][] girdi;
    String[] beklenen;
    boolean uygunluk = false;
   // File file;
    neural[] neron;
    Random generator = new Random();
    //adaline bitimi
    //----ADALİNE ALGORİTMASI----
   
    public void adalineilklendir() {
        int i, j;
        String[] lines;
        String[][] all;
        lines = jTextArea1.getText().split("\n");
        all = new String[lines.length][];
        M = lines.length;
        System.out.println("M : " + M);
        
        for( i = 0; i < M ; i++){
            all[i] = lines[i].split(" ");
        }
        N=all.length;
        System.out.println("N :" + N);
        System.out.println(all[0][0]);
        System.out.println(all[0][1]);
        neron = new neural[N-1];        
        Gate = new double[N-1];
        bias = new double[N-1];
        girdiler = new String[M][N-1];
        beklenen = new String[M];
        for( i = 0; i < N-1; i++ ){
            bias[i] = generator.nextDouble();
            //for( j = 0; j < N-1; j++){
            Gate[i]= generator.nextDouble();
            
        }
        lines = jTextArea4.getText().split("\n");
        for(i = 0;i < lines.length;i++) {
            beklenen[i] = lines[i];
        }
        System.out.println("beklenen1:" + beklenen[0]);
        System.out.println("beklenen2:" + beklenen[1]);
        for( i = 0; i < M; i++ ){
            for( j = 0; j < N-1; j++ ){
                System.out.println("hata");
                girdiler[i][j] = all[i][j];
                System.out.println("all :" + all[0][0]);
                System.out.println("all :" + all[0][1]);
                System.out.println("all :" + all[1][0]);
                System.out.println("all :" + all[1][1]);
                jTextArea2.append(girdiler[i][j]+" ");
            }
            //beklenen[i] = all[i][j];
        }
        
    }
     public void adalineciktihesap(){
        int i, j;
        
        for( j = 0; j < N-1; j++){
         //   for( i = 0; i < N-1; i++){
                neron[j].net = 0;
           // }
        }
        for( j = 0; j < N-1; j++){
            for( i = 0; i < N-1; i++){
                neron[j].net += Gate[j]*Double.parseDouble(girdiler[index][j]);
            }
            neron[j].net += bias[j];
            
            if( neron[j].net < 0){
                neron[j].sonuc = -1;
            }
            else {
                neron[j].sonuc = 1;
            }
            if( Double.parseDouble(beklenen[index]) != neron[j].sonuc){
                uygunluk = true;
            }
        }
    }
    public void adalineagirlikdegis(){
        int i, j;
        
        for( i = 0; i < N-1; i++){
           for( j = 0; j < N-1; j++){
                 Gate[i] = Gate[i] + alfa*(Double.parseDouble(beklenen[index]) - neron[i].sonuc)*Double.parseDouble(girdiler[index][j]);
           }
            bias[i] = bias[i] + alfa*(Double.parseDouble(beklenen[index]) - neron[i].sonuc);
        }
    }
    public int adalinesonuc(){
       int i, j, result = 0;

        for( j = 0; j < N-1; j++){
            //for( i = 0; i < N-1; i++){
                neron[j].net = 0;
            //}
       }
       for( j = 0; j < N-1; j++){
            for( i = 0; i < N-1; i++){
                neron[j].net += Gate[j]*Double.parseDouble(all[index][i]);
            }
            neron[j].net += bias[j];
       
            
            if( neron[j].net < 0){
                neron[j].sonuc = -1;
            }
            else {
                neron[j].sonuc = 1;
            }
            result += neron[j].sonuc;
       }
        return result/(N-1);
    }
    //----ART ALGORİTMASI----
    public String[][] artilklendir(){
        int i, j;
        String[] lines;
        String[][] all;
        lines = jTextArea1.getText().split("\n");
        all = new String[lines.length][];
        M = lines.length;
        for( i = 0; i < M ; i++){
            all[i] = lines[i].split(" ");
        }            
      
        //System.out.println(M);
        N = 3;
        for( i = 0; i < all.length; i++){
           for( j = 0; j < all[i].length; j++){
               jTextArea2.append(all[i][j]+" ");
           }
           jTextArea2.append("\n");
        }
        girdi = new x[M];        
        Gateileri = new double[N][M];
        Gategeri = new double[M][N];
        y = new double[M];
        
        for( i = 0; i < N; i++ ){
            for( j = 0; j < M; j++){
                y[j] = 0;
                Gategeri[j][i] = 1;
                Gateileri[i][j] = (double)1/(1+N);
            }
        }
        return all;
    }
    
    public void artprossescikti(){
        int i, j;

        for( i = 0; i < N; i++){
            for( j = 0; j < M; j++){
                y[j] += Gateileri[i][j]*Double.parseDouble(girdiler[index][j]);
            }
        }
    }
    public void artmaxfind(){
        int i;
        double max = 0;
        
        for( i = 0; i < M; i++){
            if( y[i] > max && (girdi[index].yasak.indexOf(i) == -1)){
                max = y[i];
                girdi[index].sinifi = i;
            }
        }
    }
    public int artuygunluk(){
        int i, j;
        double s1 = 0, s2 = 0;
        
        for( i = 0; i < N; i++){
            s1 += Double.parseDouble(girdiler[index][i]);
            s2 += Gategeri[girdi[index].sinifi][i]*Double.parseDouble(girdiler[index][i]);
        }
        if ((double)(s2/s1) > benzerlik )
            return 0;
        else
            return -1;
    }
    public void artagirlikdegis(){
        int i, toplam = 0;
        
        for( i = 0; i < N; i++){
            Gategeri[girdi[index].sinifi][i] = Gategeri[girdi[index].sinifi][i]*Double.parseDouble(girdiler[index][i]);
            toplam += Gategeri[girdi[index].sinifi][i];
        }
        
        for( i = 0; i < N; i++){
            Gateileri[i][girdi[index].sinifi] = Gategeri[girdi[index].sinifi][i] /(0.5 +(double)toplam);
        }
    } 
    //ART ALGORİTMASININ BİTİMİ
    
    //PERCEPTRON ALGORİTMASI
   // int arakatlen, ciktikatlen;
   
    double[] sigmacikti;
    double[] sigmaara;
    
    double[][] Gate1;
    double[] esik1;
    double[] esik1degisim;
    double[][] Gate1degisim;
    
    double[][] Gate2;
    double[][] Gate2orjinal;
    double[] esik2;
    double[] esik2degisim;
    double[][] Gate2degisim;
    
    double[][] hata;
    //Girdilen örnek sayımız, xsayısı ise girdideki x'ler
   // public static int girdilen, xsayisi, index = 0;
   // double alfa = 0.5, momentum = 0.8;
    
  //  String[][] girdi;
    //String[][] beklenen;
    process[] arakatman;
    process[] ciktikatman;
    public static int xsayisi,girdilen;
    double momentum = 0.8;
    
    String[][] all;
    String[] beklenend; 
    public void perceptronilklendir() {
        int i, j;
        String[] lines;
        
       
       // beklenen = new String[M];
        
        lines = jTextArea1.getText().split("\n");
        girdilen = lines.length;
        j = lines.length;
        System.out.println("satır sayisi :" + j);
        all = new String[lines.length][];

        for( i = 0; i < j; i++){
            all[i] = lines[i].split(" ");
        }  
        //for( i = 0; i < all.length; i++){
           //for( j = 0; j < all[i].length; j++){
            //   jTextArea2.append(all[i][j]+" ");
          // }
        //   jTextArea2.append("\n");
      //  }
        //String[] lines2; 
        xsayisi = all[girdilen-1].length;
        System.out.println("xsayisi :" + xsayisi);
            
       // System.out.println(all[0][1]);
        //System.out.println(all[1][1]);
       
        //for( i = 0; i < lines.length; i++ ){
            //for( j = 0; j < all[i].length; j++ ){
               // System.out.println("hata");
               // girdiler[i][j] = all[i][j];
              //  System.out.println("all :" + girdiler[i][j]);
            //    jTextArea2.append(girdiler[i][j]+" ");
          //  }
            //beklenen[i] = all[i][j];
        //}
        beklenen = new String[lines.length];
        lines = jTextArea4.getText().split("\n");
        for( i = 0; i < lines.length; i++){
             System.out.println(lines[i]);
             beklenen[i] = lines[i];
             System.out.println("beklenen :" + beklenen[i]);
        }  
        
        Gate1 = new double[xsayisi][arakatlen];
        esik1 = new double[arakatlen];
        esik1degisim = new double[arakatlen];
        Gate1degisim = new double[xsayisi][arakatlen];
        
        Gate2 = new double[arakatlen][ciktikatlen];
        Gate2orjinal = new double[arakatlen][ciktikatlen];
        
        esik2 = new double[ciktikatlen];
        esik2degisim = new double[ciktikatlen];        
        Gate2degisim = new double[arakatlen][ciktikatlen];
        //hatanın gidisini gormek için girdi sayımız kadar yapıyoruz.
        hata = new double[girdilen][ciktikatlen];
        
        arakatman = new process[arakatlen];
        ciktikatman = new process[ciktikatlen];
        

        for( i = 0; i < xsayisi; i++){           
            for( j = 0; j < arakatlen; j++){
                esik1[j] = generator.nextDouble();
                esik1degisim[j] = 0;                
                Gate1[i][j] = generator.nextDouble();
                Gate1degisim[i][j] = 0;
            }
        }
        for( i = 0; i < arakatlen; i++){
            for( j = 0; j < ciktikatlen; j++){
                esik2[j] = generator.nextDouble();
                esik2degisim[j] = 0;
                Gate2[i][j] = generator.nextDouble();
                Gate2degisim[i][j] = 0;
            }
        }
      //  System.out.println("hata");
    }
     
    public void perceptrongerihesap(){
        perceptronciktiagirlik();
        perceptronaraagirlik();    
    }
    public void perceptronilerihesap(){
        perceptronarasurec();
        perceptronciktisurec();
    }
    public void perceptronaraagirlik(){
        sigmaara = new double[arakatlen];
        int i, j;
        double toplam;
        
        for ( i = 0; i < arakatlen; i++){
            toplam = 0;
            for ( j = 0; j < ciktikatlen; j++ ){
                toplam += sigmacikti[j]*Gate2orjinal[i][j];
            }            
            sigmaara[i] = arakatman[i].cikti*(1-arakatman[i].cikti)*toplam;
        }
        for( i = 0; i < xsayisi; i++){
            for( j = 0; j < arakatlen; j++){
                Gate1degisim[i][j] = alfa*sigmaara[j]*Double.parseDouble(all[index][i]) + momentum*Gate1degisim[i][j];
                Gate1[i][j] += Gate1degisim[i][j];
            }
        }
        for( i = 0; i < arakatlen; i++){
            esik1degisim[i] = alfa*sigmaara[i] + momentum*esik1degisim[i];
            esik1[i] += esik1degisim[i];         
        }
    }
    public void perceptronciktiagirlik(){
        sigmacikti = new double[ciktikatlen];
        int i, j;
        
        for( i = 0; i < ciktikatlen; i++){
            sigmacikti[i] = ciktikatman[i].cikti*(1-ciktikatman[i].cikti)*hata[index][i];
        }
        for( i = 0; i < arakatlen; i++){
            for( j = 0; j < ciktikatlen; j++){
                Gate2degisim[i][j] = alfa*sigmacikti[j]*arakatman[i].cikti + momentum*Gate2degisim[i][j];
                Gate2[i][j] += Gate2degisim[i][j];
            }
        }
        for( j = 0; j < ciktikatlen; j++){
            esik2degisim[j] = alfa*sigmacikti[j] + momentum*esik2degisim[j];
            esik2[j] += esik2degisim[j];
        }
    }
    public void perceptronciktisurec(){
        int j, i;
        double toplam;
        for( j = 0; j < ciktikatlen; j++){
            toplam = 0;
            for( i = 0; i < arakatlen; i++){
                toplam += (arakatman[i].cikti)*(Gate2[i][j]);
            }
            ciktikatman[j] = new process();
            ciktikatman[j].net = toplam + esik2[j];
            ciktikatman[j].cikti = 1/(1 + Math.pow(Math.E,-1*ciktikatman[j].net));
            hata[index][j] = Double.parseDouble(beklenen[j]) - ciktikatman[j].cikti;
        }        
    }
    public void perceptronarasurec(){
        int j, i;
        double toplam;
        
        for(j = 0; j < arakatlen; j++){
            toplam = 0;
            for( i = 0; i < xsayisi; i++){
                toplam += Double.parseDouble(all[index][i])*Gate1[i][j];
                System.out.println("son:"+toplam);
            }
            arakatman[j] = new process();
            arakatman[j].net = toplam + esik1[j];
            arakatman[j].cikti = 1/(1 + Math.pow(Math.E,-1*arakatman[j].net));
           // index++;
        }
    }
    public void perceptronguncelleme(){
        
        int i, j;
        
        for ( i = 0; i < arakatlen; i++){
            for (j = 0; j < ciktikatlen; j++){
                Gate2orjinal[i][j] = Gate1[i][j];
            }
        }    
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Girdi ");

        jLabel2.setText("Benzerlik Oranı:");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Artsiniflandir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel3.setText("Çıktı");

        jButton2.setText("Eğit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Test");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Art", "Adaline", "Perceptron" }));

        jLabel4.setText("Ağ tipi seçiniz :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Hata Oranı");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Arakatman Sayısı");

        jButton4.setText("Temizle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Bilgi Ekranı");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton3)
                                            .addGap(85, 85, 85))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))))
                                .addGap(57, 57, 57))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
    int i, j, durum = 0;
    int epoch = 0;    
    index = 0;
    jTextArea2.setText("");
    benzerlik = Double.parseDouble(jTextField1.getText());
    girdiler = artilklendir();
       
    while( index < M ){
        artprossescikti();
        artmaxfind();
        while(true){
            if (artuygunluk() == 0){
                artagirlikdegis();
                jTextArea2.append(Integer.toString(index+1)+". girdinin sınıfı :  "+Integer.toString(girdi[index].sinifi)+"\n");
                break;
            }
            else{
                girdi[index].yasak.add(girdi[index].sinifi);
            }
        }
        index++;
    }

}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    // TODO add your handling code here:
    
    int epoch = 0,i, durum = 0;
    double hataorani;
    String[] lines;
    String Name = (String)jComboBox1.getSelectedItem();
   
    if(Name == "Adaline") {
        adalineilklendir();
        while( true ){
            durum += 1;
            adalineciktihesap();
            if( uygunluk ){
                adalineagirlikdegis();
                durum = 0;
            }
            uygunluk = false;
            if( index == M-1){
                index = 0;
                epoch += 1;
            }
            else{
                index += 1;
            }     
            if ( durum == M)
                break;
        }
        jTextArea2.setText(Integer.toString(epoch)+ "  " + "Epoch 'da tamamlandı.");
     }
     if (Name == "Perceptron") {
        
        arakatlen = (int) Double.parseDouble(jTextField3.getText().trim());
        hataorani = Double.parseDouble(jTextField2.getText().trim());
        ciktikatlen = 1;
        //arakatman=2;
        System.out.println("arakatlen:" + arakatlen);
            
        perceptronilklendir();
        while(true){
            durum += 1;
            perceptronilerihesap();
            if ( Math.abs(hata[index][0]) > hataorani){
                perceptrongerihesap();
                perceptronguncelleme();
                durum = 0;
            }
            if (index == (girdilen-1)){
                epoch += 1;
                index = 0;
            }
            else
                index += 1;
            if ( durum == girdilen)
                break;
            }    
        jTextArea2.setText(Integer.toString(epoch)+ "  " + "Epoch 'da tamamlandı.");     
    }
    
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
     int i,j;
     String[] lines;
     index = 0;   
     lines = jTextArea1.getText().split("\n");
     System.out.println("lines : " + lines[0]);
     //all[index] = lines[0].split(" ");
      
     //lines = jTextArea1.getText().split("\n");
       // girdilen = lines.length;
     j = lines.length;
       // System.out.println("satır sayisi :" + j);
     all = new String[lines.length][];

     for( i = 0; i < j; i++){
        all[i] = lines[i].split(" ");
     }  
    // System.out.println("all: " + all[0]);
    // System.out.println("all :" + all[1]);
     String Name = (String)jComboBox1.getSelectedItem();
     if(Name == "Adaline") {
        // System.out.println("bura");
        jTextArea4.setText(Integer.toString(adalinesonuc()));
     }
     else{
       for( j = 0; j < ciktikatlen; j++){
            jTextArea4.setText(Double.toString(ciktikatman[j].cikti));  
        }
     }
    
}//GEN-LAST:event_jButton3ActionPerformed


private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField2ActionPerformed

private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
    jTextArea1.setText("");
    jTextArea2.setText("");
    jTextArea4.setText("");
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
}//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(artdeneme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(artdeneme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(artdeneme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(artdeneme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new artdeneme().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
