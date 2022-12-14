/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jackhuang.hellominecraftlauncher.plugin.editpanels;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.jackhuang.hellominecraftlauncher.apis.DoneListener;
import org.jackhuang.hellominecraftlauncher.apis.IPluginHandler;
import org.jackhuang.hellominecraftlauncher.apis.PluginHandlerType;
import org.jackhuang.hellominecraftlauncher.apis.handlers.IAssetsHandler;
import org.jackhuang.hellominecraftlauncher.apis.utils.MessageBox;
import org.jackhuang.hellominecraftlauncher.apis.utils.Utils;
import org.jackhuang.hellominecraftlauncher.plugin.PluginHandler;
import org.jackhuang.hellominecraftlauncher.utilities.C;

/**
 *
 * @author hyh
 */
public class AssetsPanel extends javax.swing.JPanel {

    /**
     * Creates new form AssetsPanel
     */
    public AssetsPanel() {
        initComponents();
        
        setName(C.I18N.getString("AssetsDownload"));
    }
    
    public int assetsType;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstAssets = new javax.swing.JList();
        btnDownloadAllAssets = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboAssetsType = new javax.swing.JComboBox();

        jScrollPane8.setViewportView(lstAssets);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jackhuang/hellominecraftlauncher/I18N"); // NOI18N
        btnDownloadAllAssets.setText(bundle.getString("????????????")); // NOI18N
        btnDownloadAllAssets.setToolTipText("");
        btnDownloadAllAssets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadAllAssetsActionPerformed(evt);
            }
        });

        jButton4.setText(bundle.getString("??????")); // NOI18N
        jButton4.setToolTipText(bundle.getString("Refresh")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText(bundle.getString("AssetsType")); // NOI18N

        cboAssetsType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboAssetsTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAssetsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDownloadAllAssets, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboAssetsType, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDownloadAllAssets)
                .addGap(8, 8, 8)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDownloadAllAssetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadAllAssetsActionPerformed
        downloadAssets(cboAssetsType.getSelectedIndex());
    }//GEN-LAST:event_btnDownloadAllAssetsActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadAssets(cboAssetsType.getSelectedIndex(), true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboAssetsTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboAssetsTypeItemStateChanged
        if (cboAssetsType.getSelectedIndex() != -1 && !Utils.isEmpty((String) cboAssetsType.getSelectedItem())) {
            assetsType = cboAssetsType.getSelectedIndex();
            loadAssets(cboAssetsType.getSelectedIndex(), true);
        }
    }//GEN-LAST:event_cboAssetsTypeItemStateChanged

    private void downloadAssets(int type) {
        List<IPluginHandler> list = PluginHandler.getPluginHandlers(PluginHandlerType.getType("ASSETS"));
        IAssetsHandler ah = (IAssetsHandler) list.get(type);
        ah.beginDownloading();
    }
    
    private void loadAssets(int type, boolean isUser) {
        lstAssets.setModel(lstAssetsModel);
        
        lstAssetsModel.clear();
        List<IPluginHandler> list = PluginHandler.getPluginHandlers(PluginHandlerType.getType("ASSETS"));
        IAssetsHandler ah = (IAssetsHandler) list.get(type);
        ah.getList(new DoneListener<String[], Void>() {

            @Override
            public void onDone(String[] value, Void value2) {
                for(String s : value) {
                    lstAssetsModel.addElement(s);
                }
                lstAssets.updateUI();
            }
        });
    }
    
    public void prepareAssets() {
            List<IPluginHandler> list = PluginHandler.getPluginHandlers(PluginHandlerType.getType("ASSETS"));
            for (IPluginHandler str : list) {
                try {
                    IAssetsHandler ah = (IAssetsHandler)str;
                    cboAssetsType.addItem(ah.getName());
                } catch (Exception ex) {
                    Logger.getLogger(AssetsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageBox.Show(java.util.ResourceBundle.getBundle("org/jackhuang/hellominecraftlauncher/I18N").getString("CannotLoadPluginsBecauseOfAnError"));
                }
            }
            if (assetsType < list.size()) {
                cboAssetsType.setSelectedIndex(assetsType);
            }
    }
    DefaultListModel lstAssetsModel = new DefaultListModel();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownloadAllAssets;
    private javax.swing.JComboBox cboAssetsType;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList lstAssets;
    // End of variables declaration//GEN-END:variables
}
