/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wire.drawing.simulation.tool;

/**
 *
 * @author rAsHtElL
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WireDrawingSimulationTool {
    public static void main(String[] args) {
        
        WireDrawingMechanics wireDrawingMechanics = new WireDrawingMechanics();
        
        JFrame j = new JFrame();
        j.getContentPane().add(wireDrawingMechanics);
        j.setSize(1366,720);
        j.setTitle("Wire Drawing Simulation Tool");
        j.setJMenuBar(wireDrawingMechanics.menuBar);
        j.setVisible(true);
        
        j.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        j.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent windowEvent){
                if (JOptionPane.showConfirmDialog(j, "Are you sure you want to exit this window ?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }  
}
