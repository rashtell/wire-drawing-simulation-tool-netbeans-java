/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wire.drawing.simulation.tool;

/**
 *
 * @author rAsHtElL
 * @version 2.1
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class WireDrawingMechanics extends JPanel implements ActionListener {
    private static double initialDiameterScaled, initialLenghtScaled, finalDiameterScaled, finalLenghtScaled, force, initialDiameter, initialLenght, finalDiameter, finalLenght, volume, areaRatio, initialArea, finalArea, coeffOfFriction, inputTime, time, redVal, KConstant, nConstant;
    private final double pi = Math.PI;
    private String initialDiameterStr, initialLenghtStr, finalDiameterStr, finalLenghtStr;
    private boolean isInitialDiameter, isInitialLenght, isFinalDiameter, isFinalLenght, repaintFinished, isGeneralErrorFlag;
    private static int x1, x2, x3, x4, x5, x6, x7, x9, x10, x8, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24;
    private static int y1, y2, y3, y4, y5, y6, y7, y8, y9, y10, y11, y12, y13, y14, y15, y16, y17, y18, y19, y20, y21, y22, y23, y24;
    private static int j, k, ratio;

    private Timer timer;

    public WireDrawingMechanics() {
        initComponents();
        jPanel1.add(menuBar, 0);
        jPanel1.add(menuBar, 0);

        initCord();

        timer();
        repaintFinished = false;
        isGeneralErrorFlag = false;

        kInput.setText(Double.toString(setYieldStress()[0]));
        nInput.setText(Double.toString(setYieldStress()[1]));
    }

    private JTextField appAngInput;
    private JLabel appAngLabel;
    private JTextField coeffInput;
    private JLabel coeffLabel;
    private JButton drawButton;
    private JMenu editMenu;
    private JMenuItem exitMenuItem;
    private JMenu fileMenu;
    private JTextField finDiaInput;
    private JLabel finDiaLabel;
    private JTextField finLenInput;
    private JLabel finLenLabel;
    private JTextField forceInput;
    private JLabel forceLabel;
    private JMenu helpMenu;
    private JTextField iniDiaInput;
    private JLabel iniDiaLabel;
    private JTextField iniLenInput;
    private JLabel iniLenLabel;
    private JComboBox<String> jComboBox1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    protected JMenuBar menuBar;
    private JLabel inputsJpanel1HeaderLabel;
    private JLabel scaleLabel;
    private JTextField timeInput;
    private JLabel timeLabel;
    private JLabel zoomLabel;
    private JLabel zoomSepLabel;
    private JTextField zoomXInput;
    private JTextField zoomYInput;
    private JLabel DrawingStressLabel;
    private JTextField DrawingStressInput;
    private JLabel DiePressureLabel;
    private JTextField DiePressureInput;
    private JLabel DrawingPowerLabel;
    private JTextField DrawingPowerInput;
    private JLabel outputJpanelHeaderLabel;
    private JLabel kLabel;
    private JTextField kInput;
    private JLabel nLabel;
    private JTextField nInput;

    private void initComponents() {

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
        editMenu = new JMenu();
        helpMenu = new JMenu();
        jPanel1 = new JPanel();
        iniDiaLabel = new JLabel();
        iniLenLabel = new JLabel();
        finDiaLabel = new JLabel();
        coeffLabel = new JLabel();
        jComboBox1 = new JComboBox<>();
        appAngLabel = new JLabel();
        iniDiaInput = new JTextField();
        iniLenInput = new JTextField();
        finDiaInput = new JTextField();
        coeffInput = new JTextField();
        appAngInput = new JTextField();
        drawButton = new JButton();
        inputsJpanel1HeaderLabel = new JLabel();
        jPanel2 = new JPanel();
        scaleLabel = new JLabel();
        zoomLabel = new JLabel();
        zoomXInput = new JTextField();
        zoomSepLabel = new JLabel();
        zoomYInput = new JTextField();
        timeLabel = new JLabel();
        timeInput = new JTextField();
        jPanel3 = new JPanel();
        forceLabel = new JLabel();
        forceInput = new JTextField();
        finLenLabel = new JLabel();
        finLenInput = new JTextField();
        outputJpanelHeaderLabel = new JLabel();
        DrawingStressLabel = new JLabel();
        DrawingStressInput = new JTextField();
        DiePressureLabel = new JLabel();
        DiePressureInput = new JTextField();
        DrawingPowerLabel = new JLabel();
        DrawingPowerInput = new JTextField();
        kLabel = new JLabel();
        kInput = new JTextField();
        nLabel = new JLabel();
        nInput = new JTextField();

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener((ActionEvent evt) -> {
            exitMenuItemActionPerformed(evt);
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

//        editMenu.setText("Edit");
//        menuBar.add(editMenu);
//
//        helpMenu.setText("Help");
//        menuBar.add(helpMenu);
        setBackground(new Color(255, 102, 102));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setInheritsPopupMenu(true);
        setName("rAsHtElL"); // NOI18N

        jPanel1.setBackground(new Color(153, 153, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                Color.orange, Color.black, Color.pink, Color.red));

        iniDiaLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        iniDiaLabel.setText("Initial Diameter (mm): ");

        iniLenLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        iniLenLabel.setText("Initial Lenght (mm): ");

        finDiaLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        finDiaLabel.setText("Final Diameter (mm): ");

        coeffLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        coeffLabel.setText("Coeff of Friction: ");

        jComboBox1.setBackground(new Color(204, 204, 0));
        jComboBox1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Aluminum 1100-O", "Aluminum 2024-T4", "Aluminum 5052-O", "Aluminum 6061-O", "Aluminum 6061-T6", "Aluminum 7075-O", "Brass 70-30 annealed", "Brass 85-15 cold rolled", "Bronze(phosphor) annealed", "Cobalt-base alloy heat treated", "Copper annealed", "Molybdenum annealed", "Steel low carbon annealed", "Steel 1045 hot rolled", "Steel 1112 annealed", "Steel 1112 cold rolled", "Steel 4135 annealed", "Steel 4135 cold rolled", "Steel 4340 annealed", "Steel 17-4 P-H annealed", "Steel S2100 annealed", "Steel 304 stainless annealed", "Steel 410 stainless annealed", "Others"}));
        jComboBox1.setToolTipText("Select the desired wire material to be drawn");
        jComboBox1.addItemListener((ItemEvent evt) -> {
            jComboBox1ItemStateChanged(evt);
        });

        appAngLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        appAngLabel.setText("Approach Angle (deg): ");

        iniDiaInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N

        iniLenInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N

        finDiaInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N

        coeffInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        coeffInput.setText("0.3");

        appAngInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        appAngInput.setText("8");
        appAngInput.setToolTipText("The default approach angle is 8");

        kLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N;
        kLabel.setText("K (MPa): ");

        kInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N;
        kInput.setEnabled(false);

        nLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N;
        nLabel.setText("n: ");

        nInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N;
        nInput.setEnabled(false);

        timeLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        timeLabel.setText("Time (s): ");
        timeLabel.setToolTipText("Time (seconds) for completing the process");

        timeInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        timeInput.setText("60");
        timeInput.setToolTipText("Input the time (seconds) for completing the process");

        drawButton.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        drawButton.setText("Draw");
        drawButton.addActionListener((ActionEvent evt) -> {
            drawButtonActionPerformed(evt);
        });

        inputsJpanel1HeaderLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        inputsJpanel1HeaderLabel.setText("Inputs");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8)
                                        .addComponent(kLabel).addGap(110)
                                        .addComponent(kInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8)
                                        .addComponent(nLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(drawButton, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                .addComponent(inputsJpanel1HeaderLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(iniDiaLabel)
                                                        .addComponent(iniLenLabel)
                                                        .addComponent(coeffLabel)
                                                ).addGap(141, 141, 141))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(appAngLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(appAngInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(timeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(timeInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(finDiaLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(finDiaInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 114, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(coeffInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(iniLenInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(iniDiaInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                )
                                                        )
                                                ).addGap(93, 93, 93)
                                        )
                                )
                        )
                )
        );

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10)
                        .addComponent(drawButton).addGap(20, 20, 20)
                        .addComponent(inputsJpanel1HeaderLabel).addGap(20, 20, 20)
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(kInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(kLabel)).addGap(10)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nLabel)).addContainerGap(300, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(216, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(iniDiaLabel)
                                        .addComponent(iniDiaInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(iniLenLabel)
                                        .addComponent(iniLenInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(finDiaInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(finDiaLabel)).addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(coeffLabel)
                                        .addComponent(coeffInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(timeLabel)
                                        .addComponent(timeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(appAngLabel)
                                        .addComponent(appAngInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(kLabel)
                                        .addComponent(kInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nLabel)
                                        .addComponent(nInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                                .addContainerGap()
                        ).addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createParallelGroup())
                )
        );

        jPanel2.setBackground(new Color(153, 153, 255));

        scaleLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        scaleLabel.setText("Scale: x : y");

        zoomLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        zoomLabel.setText("Zoom");

        zoomXInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        zoomXInput.setText("1");
        zoomXInput.setToolTipText("Input the desired x cordinate zoom level");

        zoomSepLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        zoomSepLabel.setText("x");

        zoomYInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        zoomYInput.setText("1");
        zoomYInput.setToolTipText("Input the desired y cordinate zoom level");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
                        .addComponent(scaleLabel).addGap(20, 20, 20)
                        .addComponent(zoomLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomXInput, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomSepLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomYInput, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)
                )
        );

        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(scaleLabel)
                                .addComponent(zoomLabel)
                                .addComponent(zoomXInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(zoomSepLabel)
                                .addComponent(zoomYInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                )
        );

        jPanel3.setBackground(new Color(0, 153, 0));

        //Jpanel3 Heading
        outputJpanelHeaderLabel.setFont(new Font("Tahoma", 1, 14));
        outputJpanelHeaderLabel.setText("Outputs");
        //Final Lenght Output
        finLenLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        finLenLabel.setText("Final Lenght (mm): ");

        finLenInput.setEditable(false);
        finLenInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N

        //Drawing Force Output
        forceLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        forceLabel.setText("Drawing Force (N):    ");

        forceInput.setEditable(false);
        forceInput.setFont(new Font("Tahoma", 1, 14)); // NOI18N

        //Drawing Stress Output
        DrawingStressLabel.setFont(new Font("Tahoma", 1, 14));
        DrawingStressLabel.setText("Drawing Stress (MPa): ");

        DrawingStressInput.setEditable(false);
        DrawingStressInput.setFont(new Font("Tahoma", 1, 14));

        //Die Pressure Output
        DiePressureLabel.setFont(new Font("Tahoma", 1, 14));
        DiePressureLabel.setText("Die Pressure (MPa): ");

        DiePressureInput.setEditable(false);
        DiePressureInput.setFont(new Font("Tahoma", 1, 14));

        //Drawing Power Output
        DrawingPowerLabel.setFont(new Font("Tahoma", 1, 14));
        DrawingPowerLabel.setText("Drawing Power (MW):");

        DrawingPowerInput.setEditable(false);
        DrawingPowerInput.setFont(new Font("Tahoma", 1, 14));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(outputJpanelHeaderLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(finLenLabel).addGap(30)
                                        .addComponent(finLenInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(forceLabel).addGap(18)
                                        .addComponent(forceInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(DrawingStressLabel).addGap(8)
                                        .addComponent(DrawingStressInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(DiePressureLabel).addGap(27)
                                        .addComponent(DiePressureInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(DrawingPowerLabel).addGap(15)
                                        .addComponent(DrawingPowerInput, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        ).addContainerGap())
        );

        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(36, Short.MAX_VALUE)
                        .addComponent(outputJpanelHeaderLabel).addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(finLenLabel)
                                .addComponent(finLenInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(forceLabel)
                                .addComponent(forceInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(DrawingStressLabel)
                                .addComponent(DrawingStressInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(DiePressureLabel)
                                .addComponent(DiePressureInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(DrawingPowerLabel)
                                .addComponent(DrawingPowerInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)).addGap(30, 30, 30)
                )
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(576, 576, 576)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap())
                        )
                )
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(1, 1, 1)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addContainerGap(118, Short.MAX_VALUE)));

        getAccessibleContext().setAccessibleName("jpanel0");
    }// </editor-fold>

    private void drawButtonActionPerformed(ActionEvent evt) {
        timer.stop();
        repaintFinished = false;
        setOutputs();
        if (initialDiameter >= finalDiameter) {
            setScale();
            setScale();
            timer();
            timer.stop();
        }

    }

    private void jComboBox1ItemStateChanged(ItemEvent evt) {
        // Enables the Others input box if others is selected in the combo box
        if (jComboBox1.getSelectedIndex() == 23) {
            kInput.setEnabled(true);
            nInput.setEnabled(true);

        } else {
            kInput.setEnabled(false);
            nInput.setEnabled(false);
            kInput.setText(Double.toString(setYieldStress()[0]));
            nInput.setText(Double.toString(setYieldStress()[1]));

        }

        kInput.setText(Double.toString(setYieldStress()[0]));
        nInput.setText(Double.toString(setYieldStress()[1]));

    }

    private void exitMenuItemActionPerformed(ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit this window ?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
    }

    private void initCord() {
        // initial rect coordinates for constructor call
        x19 = 545;
        y19 = 280;
        x9 = 300;
        y9 = 280;
        x10 = 300;
        y10 = 430;
        x20 = 545;
        y20 = 430;

        // final rect coordinates for constructor call
        x21 = 690;
        y21 = 340;
        x22 = 690;
        y22 = 370;
        x23 = 690;
        y23 = 370;
        x24 = 690;
        y24 = 340;

        // upper die cordinates for constructor call
        x8 = 500;
        y8 = 180;
        x1 = 545;
        y1 = 180;
        x2 = 750;
        y2 = 180;
        x3 = 750;
        y3 = 300;
        x4 = 730;
        y4 = 340;
        x5 = 690;
        y5 = 340;
        x6 = 545;
        y6 = 280;
        x7 = 500;
        y7 = 260;

        // lower die cordinates for constructor call
        x12 = 690;
        y12 = 370;
        x13 = 730;
        y13 = 370;
        x14 = 750;
        y14 = 410;
        x15 = 750;
        y15 = 530;
        x16 = 545;
        y16 = 530;
        x17 = 500;
        y17 = 530;
        x18 = 500;
        y18 = 450;
        x11 = 545;
        y11 = 430;

    }

    public double[] calculateDimensions() {
        
        isGeneralErrorFlag = false;
        
        try {
            // get and assign users dimensions input
            initialDiameterStr = iniDiaInput.getText();
            initialLenghtStr = iniLenInput.getText();
            finalDiameterStr = finDiaInput.getText();
            finalLenghtStr = finLenInput.getText();

            // assigns a boolean variable to check if the input is empty
            isInitialDiameter = (initialDiameterStr.equals(""));
            isInitialLenght = (initialLenghtStr.equals(""));
            isFinalDiameter = (finalDiameterStr.equals(""));
            isFinalLenght = (finalLenghtStr.equals(""));

            // validates that the user entered something as input for the initial diameter
            if (!isInitialDiameter) {
                // assigns the initial diameter
                initialDiameter = Double.parseDouble(initialDiameterStr);
                iniDiaInput.setText(Double.toString(initialDiameter));
            } else {
                JOptionPane.showMessageDialog(this, "Input your initial diameter");
                isGeneralErrorFlag = true;
                return null;
            }

            // validates that the user entered something as input for the initial lenght
            if (!isInitialLenght) {
                // assigns the initial lenght
                initialLenght = Double.parseDouble(initialLenghtStr);
                iniLenInput.setText(Double.toString(initialLenght));
            } else {
                JOptionPane.showMessageDialog(this, "Input your initial lenght");
                isGeneralErrorFlag = true;
                return null;
            }

            // calculates the initial volume
            volume = ((pi * (Math.pow(initialDiameter, 2)) * initialLenght) / 4);

            // calculates the initial area
            initialArea = ((pi * (Math.pow(initialDiameter, 2))) / 4);

            // validates if the user entered something as input for the final diameter
            if (!isFinalDiameter) {
                // assigns the final diameter
                finalDiameter = Double.parseDouble(finalDiameterStr);

                // calculates the final length and sets its value
                finalLenght = ((volume * 4) / ((Math.pow(finalDiameter, 2)) * pi));
                finalArea = ((pi * (Math.pow(finalDiameter, 2))) / 4);
                
                finDiaInput.setText(Double.toString(finalDiameter));

                DecimalFormat f = new DecimalFormat("##.0000");
                finalLenght = Double.parseDouble(f.format(finalLenght));
                finLenInput.setText(Double.toString(finalLenght));

                // OR
                // validates if the user entered something as input for the final lenght
            } else if (!isFinalLenght) {
                // assigns the final length
                finalLenght = Double.parseDouble(finalLenghtStr);

                // calculates the final length and sets its value
                finalDiameter = Math.sqrt((volume * 4) / (finalLenght * pi));

                finalArea = ((pi * (Math.pow(finalDiameter, 2))) / 4);
                
                finLenInput.setText(Double.toString(finalLenght));
                
                DecimalFormat f = new DecimalFormat("##.0000");
                finalDiameter = Double.parseDouble(f.format(finalDiameter));
                finDiaInput.setText(Double.toString(finalDiameter));

            } else {
                JOptionPane.showMessageDialog(this, "Input your final lenght OR diameter");
                isGeneralErrorFlag = true;
                return null;
            }

        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid input", "Error Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return new double[]{initialDiameter, finalLenght, initialLenght, finalLenght, initialArea, finalArea, volume};

    }

    public double calculateTanOfDieAngle() {

        // sets the default die angle
        double angle;
        try {
            // gets the input approach angle
            angle = Double.parseDouble(appAngInput.getText());

            // converts the angle from degree to radians and finds its tangent
            angle = (Math.tan(Math.toRadians(angle)));
            // JOptionPane.showMessageDialog(this, "angle = " + angle);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Input a valid angle of approach");
            return 0;
        }
        return angle;
    }

    public final double[] setYieldStress() {
        // assigns a default value
        int minYield = 0;
        int maxYield = 0;

        // switch statement to set the yield strength according to the selected
        // material in the combo box
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                KConstant = 180;
                nConstant = 0.2;
                break;
            case 1:
                KConstant = 690;
                nConstant = 0.16;
                break;
            case 2:
                KConstant = 210;
                nConstant = 0.13;
                break;
            case 3:
                KConstant = 205;
                nConstant = 0.2;
                break;
            case 4:
                KConstant = 410;
                nConstant = 0.05;
                break;
            case 5:
                KConstant = 400;
                nConstant = 0.17;
                break;
            case 6:
                KConstant = 895;
                nConstant = 0.49;
                break;
            case 7:
                KConstant = 580;
                nConstant = 0.34;
                break;
            case 8:
                KConstant = 720;
                nConstant = 0.46;
                break;
            case 9:
                KConstant = 2070;
                nConstant = 0.5;
                break;
            case 10:
                KConstant = 315;
                nConstant = 0.54;
                break;
            case 11:
                KConstant = 725;
                nConstant = 0.13;
                break;
            case 12:
                KConstant = 530;
                nConstant = 0.26;
                break;
            case 13:
                KConstant = 965;
                nConstant = 0.14;
                break;
            case 14:
                KConstant = 760;
                nConstant = 0.19;
                break;
            case 15:
                KConstant = 760;
                nConstant = 0.08;
                break;
            case 16:
                KConstant = 1015;
                nConstant = 0.17;
                break;
            case 17:
                KConstant = 1100;
                nConstant = 0.14;
                break;
            case 18:
                KConstant = 640;
                nConstant = 0.15;
                break;
            case 19:
                KConstant = 1200;
                nConstant = 0.05;
                break;
            case 20:
                KConstant = 1450;
                nConstant = 0.07;
                break;
            case 21:
                KConstant = 1275;
                nConstant = 0.45;
                break;
            case 22:
                KConstant = 960;
                nConstant = 0.1;
                break;
            case 23:
                KConstant = Double.parseDouble(kInput.getText());
                kInput.setText(Double.toString(KConstant));

                nConstant = Double.parseDouble(nInput.getText());
                nInput.setText(Double.toString(nConstant));
                break;
        }
        return new double[]{KConstant, nConstant};
    }

    /**
     *
     * @return
     */
    public double[] calculateDrawProperties() {
        double[] constants = setYieldStress();
        double K = constants[0];
        double n = constants[1];
        double drawForce;
        double avgYield;
        double drawStress;
        double dieAngle;
        double strain;
        double diePressure;
        double flowStress;
        double stress;
        double flowVelocity;
        double power;

        
        //force = Math.ceil((averageYield * ((Math.log(areaRatio)) * 2.302585093)) * (1 + (coeffOfFriction * (1 / angle))) * redVal);
        
        if (calculateDimensions() == null) {
            return null;
        }

        try {

            dieAngle = Double.parseDouble(appAngInput.getText());
            dieAngle = Math.toRadians(dieAngle);
            
            // assigns the coefficient of friction
            coeffOfFriction = Double.parseDouble(coeffInput.getText());
            coeffInput.setText(Double.toString(coeffOfFriction));
            
            //assigns time
            inputTime = Double.parseDouble(timeInput.getText());
            
            // calculates the ratio of the initial area to the final area
            areaRatio = initialArea / finalArea;
            
            double initialDiameterSquared = Math.pow(initialDiameter, 2);
            double finalDiameterSquared = Math.pow(finalDiameter, 2);
            //calculates strain
            strain = (Math.log(initialDiameterSquared / finalDiameterSquared));

            //calculates the average yield stress
            avgYield = ((K * Math.pow(strain, n)) / (n + 1));

            //calculates the draw stress
            drawStress = (avgYield * ((((1 + (coeffOfFriction / dieAngle)) * strain) + ((((double) 2) / 3) * dieAngle))));

            //calculates the draw force
            drawForce = (drawStress * (finalArea/1000))*1000;

            //calculates the flow stress
            flowStress = K * Math.pow(strain, n);

            //calculates
            stress = (avgYield * finalArea * strain) / finalArea;

            diePressure = flowStress - drawStress;
//            System.out.println(flowStress - drawStress);
//            System.out.println(flowStress - stress);

            flowVelocity = (finalLenght/1000) / inputTime;

            power = (drawForce * flowVelocity)/1000000;
//            System.out.println((drawStress*flowVelocity)/1000000);

            time = flowVelocity;

            DecimalFormat f = new DecimalFormat("##.0000");

            drawForce = Double.parseDouble(f.format(drawForce));
            drawStress = Double.parseDouble(f.format(drawStress));
            diePressure = Double.parseDouble(f.format(diePressure));
            power = Double.parseDouble(f.format(power));

            return new double[]{drawForce, drawStress, diePressure, power, flowStress, stress, strain, flowVelocity, K, n};

        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Check your inputs");
            isGeneralErrorFlag = true;
            return null;
        }        
    }

    private void setOutputs() {

        try {
            // sets the input boxes according to their rightful value
            double[] values;

            values = calculateDrawProperties();
            if (values == null) {
                return;
            }

            forceInput.setText(Double.toString(values[0]));
            DrawingStressInput.setText(Double.toString(values[1]));
            DiePressureInput.setText(Double.toString(values[2]));
            DrawingPowerInput.setText(Double.toString(values[3]));
            kInput.setText(Double.toString(KConstant));
            nInput.setText(Double.toString(nConstant));
        } catch (Exception e) {
        }
    }

    public void setCordinates() {

        double angle = calculateTanOfDieAngle();

        // calculates and sets the coordinates for the upper die
        x5 = (int) Math.ceil(800 - initialLenghtScaled - ((initialDiameterScaled - finalDiameterScaled) / (2 * (angle))));
//        x5 = 
        y5 = (int) (300 - (finalDiameterScaled / 2));
        x6 = (int) Math.ceil(x5 - ((initialDiameterScaled - finalDiameterScaled) / (2 * (angle))));
        y6 = (int) (y5 - ((initialDiameterScaled - finalDiameterScaled) / 2));
        x4 = x5 + 50;
        y4 = y5;
        x3 = x4 + 20;
        y3 = (int) (y4 - finalDiameterScaled);
        x2 = x3;
        y2 = (int) (y3 - initialDiameterScaled);
        x1 = x6;
        y1 = y2;
        x8 = (x1 - ((((x2 - x1) / 10) / 4)));
        y8 = y1;
        x7 = x8;
        y7 = (int) (Math.ceil(y6 - ((x1 - x8) * ((angle)))));

        // calculates and sets the coordinates for the init rect
        x19 = x6;
        y19 = y6;
        x9 = (int) (x19 - initialLenghtScaled);
        y9 = y19;
        x10 = x9;
        y10 = (int) (y9 + initialDiameterScaled);
        x20 = x19;
        y20 = y10;

        // calculates and sets the coordinates for the lower die
        x11 = x20;
        y11 = y20;
        x12 = x5;
        y12 = (int) (y5 + finalDiameterScaled);
        x13 = x4;
        y13 = y12;
        x14 = x3;
        y14 = (int) (y13 + finalDiameterScaled);
        x15 = x14;
        y15 = (int) (y14 + initialDiameterScaled);
        x16 = x1;
        y16 = y15;
        x17 = x8;
        y17 = y16;
        x18 = x17;
        y18 = y17 - (y7 - y8);

        // calculates and sets the coordinates for the final rect
        x21 = x5;
        y21 = y5;
        x22 = x12;
        y22 = y12;
        x23 = x22;
        y23 = y22;
        x24 = x23;
        y24 = y21;

        // System.out.println(x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5 + ", "
        // + x6 + ", " + x7 + ", " + x9 + ", " + x10 + ", " + x8 + ", " + x11 + ", " +
        // x12 + ", " + x13 + ", " + x14 + ", " + x15 + ", " + x16 + ", " + x17 + ", " +
        // x18 + ", " + x19 + ", " + x20 + ", " + x21 + ", " + x22 + ", " + x23 + ", " +
        // x24);
        // System.out.println(y1 + ", " + y2 + ", " + y3 + ", " + y4 + ", " + y5 + ", "
        // + y6 + ", " + y7 + ", " + y9 + ", " + y10 + ", " + y8 + ", " + y11 + ", " +
        // y12 + ", " + y13 + ", " + y14 + ", " + y15 + ", " + y16 + ", " + y17 + ", " +
        // y18 + ", " + y19 + ", " + y20 + ", " + y21 + ", " + y22 + ", " + y23 + ", " +
        // y24);
    }

    public void setScale() {

        try {
            // sets the cordinates
            setCordinates();

            // assigns the X and Y zoom cordinates
            int m = Integer.parseInt((zoomXInput.getText()));
            int n = Integer.parseInt((zoomYInput.getText()));

            // scales the initial and final dimensions(diameters and lenght) according to
            // the zoom value
            initialDiameterScaled = (int) Math.ceil(initialDiameter * m);
            initialLenghtScaled = (int) Math.ceil(initialLenght * n);
            finalDiameterScaled = (int) Math.ceil(finalDiameter * m);
            finalLenghtScaled = (int) Math.ceil(finalLenght * n);

            // sets the ratio of the final lenght to the initial lenght
            ratio = (int) Math.ceil(finalLenght / initialLenght);

            // makes sure the coordinates does not exceed the screen
            while ( x19 < 500) {
                // checks if the coordinate exceeds the screen's width
                if ( x19 < 500) {

                    // All x-axis should be reduced by a scale
                    initialLenghtScaled = initialLenghtScaled / 2;
                    finalLenghtScaled = finalLenghtScaled / 2;
                    setCordinates();
                    j++;
                }
                setCordinates();
                if (j > 1000000) {
                    JOptionPane.showMessageDialog(this, "A scale error occured. Please change your zoom parameter");
                    break;
                }

//                System.out.println("j = " + j + ", k = " + k + " x23 = " + x23 + " x19 = " + x19);
            }

            while (y15 > 720 || y2 < 50) {

                // checks if the coordinate exceeds the screen's height
                if (y15 > 720 || y2 < 50) {

                    // All y-axis should be reduced by a scale
                    initialDiameterScaled = initialDiameterScaled / 2;
                    finalDiameterScaled = finalDiameterScaled / 2;
                    setCordinates();
                    k++;
                }
                setCordinates();

                if (k > 10000000) {                    
                    JOptionPane.showMessageDialog(this, "A scale error occured. Please change your zoom parameter");
                    break;
                }
//                System.out.println("j = " + j + ", k = " + k + " y15 = " + y15 + " y2 = " + y2);
            }

            // sets the scaleLabel text
            scaleLabel.setText("SCALE: " + (m * j) + " : " + (n * k));

            j = 1;
            k = 1;

        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(WireDrawingMechanics.this, "Scale error");
        }

    }

    public void customGraphics(Graphics g) {

        // the ratio between the final rect and initial rect
        ratio = (int) Math.ceil(finalLenght / initialLenght);

        // paints the initial rect
        g.setColor(Color.DARK_GRAY);
        int[] a = {x19, x9, x10, x20};
        int[] b = {y19, y9, y10, y20};
        g.fillPolygon(a, b, 4);

        // paints the final rect
        g.setColor(Color.BLACK);
        int[] c = {x21, x22, x23, x24};
        int[] e = {y21, y22, y23, y24};
        g.fillPolygon(c, e, 4);

        // paints the upper die
        g.setColor(Color.ORANGE);
        int x[] = {x6, x5, x4, x3, x2, x1, x8, x7};
        int y[] = {y6, y5, y4, y3, y2, y1, y8, y7};
        g.fillPolygon(x, y, 8);

        // paints the lower die
        int m[] = {x11, x12, x13, x14, x15, x16, x17, x18};
        int n[] = {y11, y12, y13, y14, y15, y16, y17, y18};
        g.fillPolygon(m, n, 8);

        // Increments the value of each cordinate after each method call
        // to create an animation
        x9 += 1;
        x10 += 1;
        x19 += 1;
        x20 += 1;
        a[0] = x19;
        a[1] = x9;
        a[2] = x10;
        a[3] = x20;
    }

    public void customgraphics2(Graphics g) {

        // paints the initial rect
        g.setColor(Color.DARK_GRAY);
        int[] a = {x19, x9, x10, x20};
        int[] b = {y19, y9, y10, y20};
        g.fillPolygon(a, b, 4);

        // paints the final rect
        g.setColor(Color.BLACK);
        int[] c = {x21, x22, x23, x24};
        int[] e = {y21, y22, y23, y24};
        g.fillPolygon(c, e, 4);

        // paints the upper die
        g.setColor(Color.ORANGE);
        int p[] = {x6, x5, x4, x3, x2, x1, x8, x7};
        int q[] = {y6, y5, y4, y3, y2, y1, y8, y7};
        g.fillPolygon(p, q, 8);

        // paints the lower die
        int m[] = {x11, x12, x13, x14, x15, x16, x17, x18};
        int n[] = {y11, y12, y13, y14, y15, y16, y17, y18};
        g.fillPolygon(m, n, 8);

        // increments the value of the cordinates after each method call
        // to create an animation
        x9 += 1;
        x10 += 1;

        // System.out.println(ratio);
        // System.out.println(y20 + " " + y19 + " " + y22 + " " + y21 + " ");
        if (y20 - y19 != 0 || y22 - y21 != 0) {
            ratio = ((y20 - y19) / (y22 - y21));
        }
        // System.out.println("ratio: " + ratio);

        x23 = x23 + ratio;
        x24 = x24 + ratio;

        a[1] = x9;
        a[2] = x10;
        c[2] = x23;
        c[3] = x24;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGeneralErrorFlag) return;

        // System.out.println("Before customGraphics()");
        // calls the first graphics method
        if (!repaintFinished) {
            customGraphics(g);

        }
        // System.out.println("After customGraphics()");

        // System.out.println("Before customGraphics2()");
        // calls the second graphics method after the first graphics is done with
        if (repaintFinished) {
            customgraphics2(g);

        }
        // System.out.println("After customGraphics2()");

    }

    private void timer() {
        if (isGeneralErrorFlag) return;
        Thread t = new Thread() {
            @Override
            public void run() {
                inputTime = Double.parseDouble(timeInput.getText());
//                time = (int) Math.floor((inputTime * force) / 100000);
                timer = new Timer((int) inputTime, WireDrawingMechanics.this);
                timer.setInitialDelay(0);
                timer.setCoalesce(true);
                timer.start();

                repaintFinished = false;
            }

        };
        t.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGeneralErrorFlag) return;

        Thread t = new Thread() {
            @Override
            public void run() {
                // calls the customGraphics method in the paintComponent method
                // iterates until x19 reaches x5 #movement
                // System.out.println("Before: " + "x19 = " + x19 + " x5 = " + x5);
                for (int i = x19; i <= x5; i++) {

                    // this method call repaints calls the paintComponet method
                    WireDrawingMechanics.this.repaint();

                }
                // System.out.println("After: " + "x19 = " + x19 + " x5 = " + x5);

                // repaintFinished = true;
                // checks if the initial rect as reached the die's throat
                if (x19 >= x5) {
                    // System.out.println("repaintFinished is true - first");

                    // stops the timer
                    timer.stop();

                    // tells the paintComponent method to only allow customGraphics2 method to be
                    // invoked
                    repaintFinished = true;

                    // System.out.println("Before: " + "x9 = " + x9 + " x19 = " + x19);
                    // call the customGraphics2 method in the paintComponent method
                    // iterate until x9 meets x19 #DiameterDecreamentIncreament
                    for (int i = x9; i <= x19; i++) {
                        // starts the timer
                        timer.start();

                        // tells the paintComponent method to only allow customGraphics2 method to be
                        // invoked
                        // double assurance that the compiler aint crazy
                        repaintFinished = true;

                        // calls the paintComponent method
                        WireDrawingMechanics.this.repaint();

                    }
                    // System.out.println("After: " + "x9 = " + x9 + " x19 = " + x19);
                }
            }
        };

        t.start();

    }

}
