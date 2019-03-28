package fr.ecp.IS1220.myVelib.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class MouseClickExample {
  public static void main(String [] args) {
 
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("MouseClickExample Example");
    frame.setSize(350,150);
 
    final JLabel label = new JLabel();
    frame.add(label);
 
    frame.addMouseListener(new MouseListener() {
        public void mousePressed(MouseEvent me) { }
        public void mouseReleased(MouseEvent me) { }
        public void mouseEntered(MouseEvent me) { }
        public void mouseExited(MouseEvent me) { }
        public void mouseClicked(MouseEvent me) { 
          if(me.getButton() == MouseEvent.BUTTON1) {
            label.setText("Left Click!");
          }
          if(me.getButton() == MouseEvent.BUTTON2) {
            label.setText("Middle Click!");
          }
          if(me.getButton() == MouseEvent.BUTTON3) {
            label.setText("Right Click!");
          }
        }
    });
 
    frame.setVisible(true);			
  }
}