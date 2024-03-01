package com.example.hirnok;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class HelloController {
   @FXML private Label lbdarab;
   @FXML private ImageView ivPlayer;
   @FXML private ImageView ivBad;
   @FXML private ImageView ivPapir;
   @FXML private ImageView ivHome;
   @FXML private ProgressBar pbHealth;
   @FXML private Pane pnJatek;

   private AnimationTimer timer = null;

   int darab=0;

   private double px=100,py=100;
   private double bx=700,by=100;
   private double hx=100,hy=500;
   private double tx=400,ty=300;
   private double mx=px,my=py;
   private double dx=0,dy=0;

   public void initialize(){
       gondolTerv();
       timer =new AnimationTimer() {
           @Override
           public void handle(long now) {
                mozgat();
           }
       };
       timer.start();
   }
   public void gondolTerv(){
       tx=Math.random()*716+42;ty=Math.random()*516+42;
       ivPapir.setLayoutX(tx-32);ivPapir.setLayoutY(ty-32);
   }
   public void mozgat(){
        if((px-mx)*(px-mx)+(py-my)*(py-my)>5*5){px+=dx;py+=dy;ivPlayer.setLayoutY(py-36);ivPlayer.setLayoutX(px-36);}
        if((px-mx)*(px-mx)+(py-my)*(py-my)<20*20){
            darab++;
            gondolTerv();
        }
       if((px-hx)*(px-hx)+(py-hy)*(py-hx)<20*20) {
           double h =pbHealth.getProgress();
           ivHome.setRotate(ivHome.getRotate()+1);

           if(h<1){h+=0.005;pbHealth.setProgress(h);}
       }
       if(bx>px)bx-=2;else bx+=2;
       if(by>py)by-=2;else by+=2;
       ivBad.setLayoutX(bx-36);ivBad.setLayoutY(by-36);

       if((px-bx)*(px-bx)+(py-by)*(py-bx)<30*30){
           pbHealth.setProgress(pbHealth.getProgress()-0.02);
       }
        lbdarab.setText(darab+" db");


   }
   private void wincondition(){
       if(pbHealth.getProgress()==0){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Vége");
           alert.setHeaderText(null);
           alert.setContentText("Meghaltál! "+darab+" tervrajzot szedtél össze");
           alert.showAndWait();
       }
   }
   @FXML private void onMousePressed(MouseEvent e){
        mx=e.getX();my=e.getY();
        if(Math.abs(px-mx)>Math.abs(py-my)) {
            if(mx>px)dx=5;else dx-=5;
            dy=(my-py)/Math.abs(mx-px)*5;
        }else{
            if(my>py)dy=5;else dy=-5;
            dx=(mx-px)/Math.abs(my-py)*5;
        }
   }

}