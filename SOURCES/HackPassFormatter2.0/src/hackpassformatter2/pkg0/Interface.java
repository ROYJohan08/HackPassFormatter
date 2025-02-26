package hackpassformatter2.pkg0;

import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class Interface extends javax.swing.JFrame {
    private Controller Control;
    private File InputFolder = null;
    public Interface(Controller Control) {
        this.Control=Control;
        initComponents();
        InputPathSelectButton.requestFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        InputPathSelectButton = new javax.swing.JButton();
        InputPath = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        FileList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        OutputPathSelectButton = new javax.swing.JButton();
        OutputPath = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        PasswordList = new javax.swing.JTable();
        ProgressBar = new javax.swing.JProgressBar();
        SaveButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HackPassFormater");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Input");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        InputPathSelectButton.setText("Parcourrir");
        InputPathSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPathSelectButtonActionPerformed(evt);
            }
        });

        InputPath.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                InputPathCaretUpdate(evt);
            }
        });

        FileList.setFocusable(false);
        jScrollPane1.setViewportView(FileList);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Output");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setMaximumSize(new java.awt.Dimension(53, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(53, 30));

        OutputPathSelectButton.setText("Parcourrir");

        PasswordList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(PasswordList);

        SaveButton.setText("Save");

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(InputPath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputPathSelectButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(OutputPath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OutputPathSelectButton))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jSeparator3)))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ExitButton)
                        .addGap(348, 348, 348)
                        .addComponent(ClearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SaveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InputPathSelectButton)
                            .addComponent(InputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OutputPathSelectButton)
                            .addComponent(OutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(ExitButton)
                    .addComponent(ClearButton))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputPathSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPathSelectButtonActionPerformed
        if(InputPath.getText().length()>2){InputFolder = Control.SelectInput(InputPath.getText());}
        else{InputFolder = Control.SelectInput();}
        InputPath.setText(Control.GetInputPath(InputFolder));
    }//GEN-LAST:event_InputPathSelectButtonActionPerformed

    private void InputPathCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_InputPathCaretUpdate
        FileList.setModel(Control.GetInputList(InputFolder));
        ProgressBar.setMaximum(Control.GetMaxValue(InputFolder));
        String[][] Pass = Control.FormatFiles(InputFolder);
        String[] Name = {"Tye","Url","Username","Mot de passe"};
        DefaultTableModel Table = new DefaultTableModel(Pass,Name);
        PasswordList.setModel(Table);
    }//GEN-LAST:event_InputPathCaretUpdate

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        Control.Stop();
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        InputPath.setText("");
        FileList.setModel(new DefaultListModel<>());
    }//GEN-LAST:event_ClearButtonActionPerformed
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface(new Controller(null)).setVisible(true);
            }
        });
    }
    public void AddValue(int i){
        int T = ProgressBar.getValue()+i;
        if(T>ProgressBar.getMaximum()){T=ProgressBar.getMaximum();}
        ProgressBar.setValue(T);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JList<String> FileList;
    private javax.swing.JTextField InputPath;
    private javax.swing.JButton InputPathSelectButton;
    private javax.swing.JTextField OutputPath;
    private javax.swing.JButton OutputPathSelectButton;
    private javax.swing.JTable PasswordList;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
