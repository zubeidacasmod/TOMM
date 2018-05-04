/*

Copyright notice
TOMM copyright 2016-2017, Zubeida C. Khan

Copying permission
This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Number;
import java.lang.Object;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author Zubeida
 */
public class start extends JFrame implements Runnable {

    private OWLEntity key;

    private int jfc1;
    private int jfc2;
    private int jfc3;

    private File module;
    private File ontology;
    private File[] moduleSetF = new File[9000];
    private OWLOntologyManager manager;
    private OWLOntologyManager manager2;

    private OWLOntologyManager managerSet[] = new OWLOntologyManager[9000];
    private OWLOntology moduleSet[] = new OWLOntology[9000];

    private OWLOntology moduleOWL;
    private OWLOntology ontologyOWL;

    private File logfile;
    private File logfile2;
    private File logfile3;

    private File[] logfileSet = new File[100000];
    private File[] logfileSet2 = new File[100000];

    private int mClass;
    private int mOP;
    private int mDP;
    private int mInd;
    private int mSize;
    private int mAx;
    private double mApp;
    private double imd;
    private double mIntramoddist;
    private double mCohesion;
    private double mAttRich;
    private double mInhRich;
    private double mAtomicSize;

    private int oClass;
    private int oOP;
    private int oDP;
    private int oInd;
    private int oSize;
    private int oAx;
    private double oApp;
    private double oIntramoddist;
    private double oCohesion;
    private double oAttRich;
    private double oInhRich;
    private double oAtomicSize;

    private double rSize;
    private double rIntramoddist;
    private boolean correctness;
    private boolean completeness;
    private double redundancy;

    private int classSet[] = new int[500000];
    private int opSet[] = new int[500000];
    private int dpSet[] = new int[500000];
    private int indSet[] = new int[500000];
    private int sizeSet[] = new int[500000];
    private int axSet[] = new int[5000000];
    private double appSet[] = new double[500000];
    private double intramoddistSet[] = new double[500000];
    private double cohesionSet[] = new double[500000];
    private double attRichSet[] = new double[500000];
    private double inhRichSet[] = new double[500000];
    private double rSizeSet[] = new double[500000];
    private double rIntramoddistSet[] = new double[500000];
    private double correctnessSet[] = new double[500000];
    private double completenessSet[] = new double[500000];
    private double redundnacySet[] = new double[500000];
    private double atomicSizeSet[] = new double[500000];

    private double encapsulation;
    private double coupling;
    private boolean independence;
    private double intermoduledistance;

    private boolean mLoaded = false;
    private boolean oLoaded = false;
    private boolean mSetLoaded = false;

    private DateFormat time = DateFormat.getTimeInstance();
    private Date date = new Date();
    
    private double tempCoh = 0;
    private double tempCoh2 = 0;

    //   private Object FileManager;
    /**
     * Creates new form NewJFrame
     */
    public start() {
        initComponents();
        jButton3.setEnabled(false);
        jButton1.setEnabled(false);

        for (int i = 0; i < classSet.length; i++) {
            classSet[i] = -1;
            opSet[i] = -1;
            dpSet[i] = -1;
            indSet[i] = -1;
            sizeSet[i] = -1;
            axSet[i] = -1;
            appSet[i] = -1;
            intramoddistSet[i] = -1;
            cohesionSet[i] = -1;
            attRichSet[i] = -1;
            inhRichSet[i] = -1;
            rSizeSet[i] = -1;
            rIntramoddistSet[i] = -1;
            correctnessSet[i] = -1;
            completenessSet[i] = -1;
            redundnacySet[i] = -1;
            atomicSizeSet[i] = -1;
        }
        
    
          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jFileChooser3 = new javax.swing.JFileChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser2ActionPerformed(evt);
            }
        });

        jFileChooser3.setDialogTitle("This is my open dialog");
        jFileChooser3.setMultiSelectionEnabled(true);
        jFileChooser3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Load original ontology");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Load module");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Run metrics");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Load module set");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TOMM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tool for Ontology Module Metrics");

        jLabel3.setText("1.");

        jLabel4.setText("2.");

        jLabel5.setText("3.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5))
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel5)))))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        // jfc1 =  jFileChooser1.showOpenDialog(this);

    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:

    }//GEN-LAST:event_jFileChooser2ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jfc2 = jFileChooser2.showOpenDialog(start.this);

        if (jfc2 == JFileChooser.CANCEL_OPTION) {
            System.out.println("File access cancelled by user.");
            //jfc2 = jFileChooser2.showOpenDialog(this);

        } //  int returnVal = jFileChooser1.showOpenDialog(this);
        else if (jfc2 == JFileChooser.APPROVE_OPTION) {
            ontology = jFileChooser2.getSelectedFile();

            try {
                jTextArea1.append("Loaded original ontology: " + ontology.getName() + "\n");
                jTextArea1.update(jTextArea1.getGraphics());

                oLoaded = true;
            } catch (Exception e) {
                System.out.println("problem accessing file " + ontology.getAbsolutePath());
            }
            jButton3.setEnabled(false);

        } else {
            System.out.println("File access cancelled by user.");

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jfc1 = jFileChooser1.showOpenDialog(start.this);

        if (jfc1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("File access cancelled by user.");
            // jfc1 = jFileChooser1.showOpenDialog(this);

        } else if (jfc1 == JFileChooser.APPROVE_OPTION) {

            module = jFileChooser1.getSelectedFile();
            //  if (!(module == null)){

            try {
                jTextArea1.append("Loaded module: " + module.getName() + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                jButton1.setEnabled(true);
                jButton2.setEnabled(false);

                jButton4.setEnabled(false);
                jButton3.setEnabled(true);

                mLoaded = true;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("problem accessing file " + module.getAbsolutePath());
            }

            // }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setProxy();
        loadOntologies();
        
        
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFileChooser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser3ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        // TODO add your handling code here:
        // int returnVal = jFileChooser1.showOpenDialog(this);


    }//GEN-LAST:event_jFileChooser3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jfc3 = jFileChooser3.showOpenDialog(this);

        if (jfc3 == JFileChooser.CANCEL_OPTION) {
            System.out.println("Files access cancelled by user.");
            // jfc3 = jFileChooser3.showOpenDialog(this);

        } else if (jfc3 == JFileChooser.APPROVE_OPTION) {
            // ontology = jFileChooser2.getSelectedFile();
            moduleSetF = jFileChooser3.getSelectedFiles();

            try {
                jTextArea1.append("Loaded module set: \n");
                jTextArea1.update(jTextArea1.getGraphics());
                jButton1.setEnabled(true);
                jButton4.setEnabled(false);
                for (int i = 0; i < moduleSetF.length; i++) {
                    if (!(moduleSetF[i].equals(null))) {
                        jTextArea1.append(" Module (" + (i + 1) + ") " + moduleSetF[i] + "\n");
                        jTextArea1.update(jTextArea1.getGraphics());
                    }
                }
                mSetLoaded = true;
            } catch (Exception e) {
                System.out.println("problem accessing files " + moduleSetF.toString());
            }
            jButton2.setEnabled(false);
            jButton3.setEnabled(true);

        } else {
            System.out.println("File access cancelled by user.");

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    //set the proxy according to the proxy.txt file
    public void setProxy() {
        System.out.println("Proxy settings:");
        String[] proparray = new String[2];
        for (int i = 0; i < proparray.length; i++) {
            proparray[i] = "";
        }

        File propertyfile = new File("proxy.txt");
        BufferedReader reader = null;
        Boolean b = false;

        Properties systemSettings = System.getProperties();
        try {
            reader = new BufferedReader(new FileReader(propertyfile));
            int i = 0;
            String temp;
            while ((temp = reader.readLine()) != null) {
                //String temp =reader.readLine();

                temp = temp.trim();
                //System.out.println(temp);
                proparray = temp.split("=");
                i++;
                if (proparray.length == 2) {

                    proparray[0] = proparray[0].trim();
                    proparray[1] = proparray[1].trim();
                    System.out.print(proparray[0] + ": ");
                    System.out.println(proparray[1]);

                    if (proparray[0].equals("start.proxyHost")) {
                        systemSettings.put("http.proxyHost", proparray[1]);
                        System.setProperties(systemSettings);
                    } else if (proparray[0].equals("start.proxyPort")) {
                        systemSettings.put("http.proxyPort", proparray[1]);
                        System.setProperties(systemSettings);
                    } else if (proparray[0].equals("start.proxyUser")) {
                        systemSettings.put("http.proxyUser", proparray[1]);
                        System.setProperties(systemSettings);
                    } else if (proparray[0].equals("start.proxyPassword")) {
                        systemSettings.put("http.proxyPassword", proparray[1]);
                        System.setProperties(systemSettings);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString() + " Error with proxy");
            e.printStackTrace();
        }

        System.out.println();
    }

    public void loadOntologies() {
        // Get hold of an ontology manager
        // File f = new File();

      

        try {

            if (mLoaded == true) {
                manager = OWLManager.createOWLOntologyManager();
                moduleOWL = manager.loadOntologyFromOntologyDocument(module);
                System.out.println("Module loaded: " + moduleOWL);
                jTextArea1.append("Module loaded: " + moduleOWL);
                jTextArea1.update(jTextArea1.getGraphics());
            }
            if (oLoaded == true) {
                manager2 = OWLManager.createOWLOntologyManager();
                ontologyOWL = manager2.loadOntologyFromOntologyDocument(ontology);
                System.out.println("Ontology loaded: " + ontologyOWL);
                jTextArea1.append("Ontology loaded: " + ontologyOWL);
                jTextArea1.update(jTextArea1.getGraphics());
            }

            if (mSetLoaded == true) {

                int temp = 0;
                for (File fOnto : moduleSetF) {
                    if ((!moduleSetF.equals(null))) {
                        System.out.println(moduleSetF[temp]);
                        managerSet[temp] = OWLManager.createOWLOntologyManager();
                        moduleSet[temp] = managerSet[temp].loadOntologyFromOntologyDocument(moduleSetF[temp]);
                        // System.out.println("loaded "+moduleSet[temp]);

                        System.out.println("Module loaded from set:" + (temp + 1) + ") " + moduleSet[temp].toString());
                        jTextArea1.append("Module loaded from set: " + (temp + 1) + ") " + moduleSet[temp].toString());
                        jTextArea1.update(jTextArea1.getGraphics());
                        temp++;

                    }
                }

            }

           System.out.println();
           metrics();
            
            
            
            //  System.out.println("here "+mSetLoaded);
        } catch (Exception e) {
            System.out.println("Failed to load ontology/ies");
            e.getLocalizedMessage();
            e.printStackTrace();
            //ioe.printStackTrace();
            //  e.printStackTrace();
        
            }
    }
    
    public void metrics() {

        float sumMS = 0;

        try{
        if (oLoaded == true) {

            System.out.println("Metrics for " + ontology);
            long mTime = System.currentTimeMillis();
            oClass = ontologyOWL.getClassesInSignature(true).size();
            oDP = ontologyOWL.getDataPropertiesInSignature(true).size();
            oOP = ontologyOWL.getObjectPropertiesInSignature(true).size();
            oInd = ontologyOWL.getIndividualsInSignature(true).size();
            oSize = oClass + oDP + oOP + oInd;
            oAx = ontologyOWL.getAxiomCount();
            Set <OWLOntology> ontos = ontologyOWL.getDirectImports();
            
            for(OWLOntology o: ontos){
                oAx += o.getAxiomCount();
            }

            System.out.println("classes: " + oClass);
            System.out.println("op: " + oOP);
            System.out.println("dp: " + oDP);
            System.out.println("ind: " + oInd);
            System.out.println("size: " + oSize);
            System.out.println("axioms: " + oAx);

      // double cos = Math.cos(oAx* (Math.PI/250));
            //  Fraction f1 =new Fraction(1,2);
            //  double ii = (.5) * (cos);
            try {

                jTextArea1.append("Processing metrics for " + ontology.getName() + " ..... \n");
                jTextArea1.update(jTextArea1.getGraphics());
                
                logfile = new File(System.getProperty("user.home") + "/Logs/" + ontology.getName() + "-" + time.format(date).replaceAll(" ", "").replaceAll(":", ".") + ".txt");
                logfile.getParentFile().mkdirs();
                FileWriter write = new FileWriter(logfile);
                BufferedWriter bwrite = new BufferedWriter(write);
                bwrite.write("Log file for " + ontology.getName() + "\n\n");

                /*System.out.println("class "+oClass);
                 System.out.println("op "+oOP);
                 System.out.println("dp "+oDP);
                 System.out.println("ind "+oInd);
                 System.out.println("size "+oSize);
                 System.out.println("axioms "+oAx);*/
                oApp = getAppropriateness(oAx);
                //System.out.println("app "+oApp);
                //System.out.println("here1");
                
                //System.out.println("here2");
                //System.out.println("intra"+ oIntramoddist);

                //getAttributeRichness(ontologyOWL);
                oAttRich = getAttributeRichness(ontologyOWL);//(double) oDP / oClass;
                oInhRich = getInheritanceRichness(ontologyOWL);
                
                int newSize = ontologyOWL.getClassesInSignature(true).size()+ontologyOWL.getObjectPropertiesInSignature(true).size()+
                 ontologyOWL.getDataPropertiesInSignature(true).size();
                
                oAtomicSize = getAtomicSize(ontologyOWL);
             //   oCohesion = getCohesion(ontologyOWL, newSize);
                //System.out.println("o att rich"+oAttRich);

                

                //System.out.println("o inh rich"+oInhRich);
                bwrite.write("\nMetrics for " + ontology.getName() + "\n\n");
                bwrite.write("No. of classes in ontology: " + oClass + "\n");
                bwrite.write("No. of OP in ontology: " + oOP + "\n");
                bwrite.write("No. of DP in ontology: " + oDP + "\n");
                bwrite.write("No. of Ind in ontology: " + oInd + "\n");
                bwrite.write("Size of ontology: " + oSize + "\n");
                bwrite.write("Atomic size of ontology: " + oAtomicSize + "\n");
                bwrite.write("No. of axioms in ontology: " + oAx + "\n");
                bwrite.write("Appropriateness of ontology: " + oApp + "\n");
              //  bwrite.write("Intra module distance: " + oIntramoddist + "\n");

               // bwrite.write("Cohesion of ontology: " + oCohesion + "\n");
                bwrite.write("Attribute richness of ontology: " + oAttRich + "\n");
                //System.out.prinltn(""+moduleOWL.getAxioms().toString()+"\n");
                bwrite.write("Inheritance richness of ontology: " + oInhRich + "\n");
                getRelationshipRichness(ontologyOWL);

                System.out.println("Processed metrics for " + ontology.getName());
                long mEndTime = System.currentTimeMillis();
                long diffTime = mEndTime - mTime;
                //timet= timet/1000;
                double mSeconds = (double) diffTime / 1000d;
                double mMinutes = (double) mSeconds / 60d;
                double mHours = (double) mMinutes / 60d;
                sumMS += mSeconds;

                bwrite.write("Time taken for processing: " + (mSeconds) + " seconds, " + mMinutes + " minutes, " + mHours + " hours.\n");

                bwrite.close();
                
                jTextArea1.append("Metrics saved to " + logfile.getAbsolutePath() + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                
                System.out.println();
                

            } catch (Exception e) {

            }

        }

        if (mLoaded == true) {
            long mTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("Metrics for " + module);
            
            mClass = moduleOWL.getClassesInSignature(true).size();
            mDP = moduleOWL.getDataPropertiesInSignature(true).size();
            mOP = moduleOWL.getObjectPropertiesInSignature(true).size();
            mInd = moduleOWL.getIndividualsInSignature(true).size();
            mSize = mClass + mDP + mOP + mInd;
            mAx = moduleOWL.getAxiomCount();
            
            
            
            Set <OWLOntology> ontos = moduleOWL.getDirectImports();
            
            for(OWLOntology m: ontos){
                mAx += m.getAxiomCount();
            }
            
            System.out.println("classes: " + mClass);
            System.out.println("op: " + mOP);
            System.out.println("dp: " + mDP);
            System.out.println("ind: " + mInd);
            System.out.println("size: " + mSize);
            System.out.println("axioms: " + mAx);

            
            mApp = getAppropriateness(mAx);
             mAtomicSize = getAtomicSize(moduleOWL);
            
            mAttRich = getAttributeRichness(moduleOWL); //(double) mDP / mClass;
            mInhRich = getInheritanceRichness(moduleOWL);
            
             int newSize = moduleOWL.getClassesInSignature(true).size()+moduleOWL.getObjectPropertiesInSignature(true).size()+
                 moduleOWL.getDataPropertiesInSignature(true).size();
            
            mIntramoddist = intraModuleDistance(moduleOWL);
            mCohesion = getCohesion(moduleOWL, newSize);
           

            try {
                jTextArea1.append("Processing metrics for " + module.getName() + " ..... \n");
                jTextArea1.update(jTextArea1.getGraphics());
                
                logfile2 = new File(System.getProperty("user.home") + "/Logs/" + module.getName() + "-" + time.format(date).replaceAll(" ", "").replaceAll(":", ".") + ".txt");
                logfile2.getParentFile().mkdirs();
                FileWriter write = new FileWriter(logfile2);
                BufferedWriter bwrite = new BufferedWriter(write);
                bwrite.write("Log file for " + module.getName() + "\n\n");

                bwrite.write("\nMetrics for " + module.getName() + "\n\n");
                bwrite.write("No. of classes in module: " + mClass + "\n");
                bwrite.write("No. of OP in module: " + mOP + "\n");
                bwrite.write("No. of DP in module: " + mDP + "\n");
                bwrite.write("No. of Ind in module: " + mInd + "\n");
                bwrite.write("Size of module: " + mSize + "\n");
                bwrite.write("Atomic size of module: " + mAtomicSize + "\n");
                bwrite.write("No. of axioms in module: " + mAx + "\n");
                bwrite.write("Appropriateness of module: " + mApp + "\n");
                bwrite.write("Intra module distance: " + mIntramoddist + "\n");

                bwrite.write("Cohesion of module: " + mCohesion + "\n");
                bwrite.write("Attribute richness of module: " + mAttRich + "\n");
                //System.out.prinltn(""+moduleOWL.getAxioms().toString()+"\n");
                bwrite.write("Inheritance richness of module: " + mInhRich + "\n");
                System.out.println("Processed " + module);

                if (oLoaded == true) {
                    jTextArea1.append("Processing relative metrics for " + module.getName() + " ..... \n");
                    jTextArea1.update(jTextArea1.getGraphics());
                   
                    System.out.println("Relative metrics for " + module);
                    bwrite.write("Log file for relative metrics for " + module.getName() + "\n\n");

                   rSize = (double) mSize / oSize;

                    bwrite.write("\nRelative Metrics for " + module.getName() + " compared to " + ontology.getName() + "\n\n");
                    bwrite.write("Relative size of module: " + rSize + "\n");
                    
                    Set<OWLClass> someClasses = moduleOWL.getClassesInSignature(true);
                    Set<OWLObjectProperty> someOp = moduleOWL.getObjectPropertiesInSignature(true);
                    Set<OWLDataProperty> someDp = moduleOWL.getDataPropertiesInSignature(true);
                    oIntramoddist = intraModuleDistance(ontologyOWL, someClasses, someOp, someDp);

                    
                    
                    if (oIntramoddist == 0 || mIntramoddist == 0){
                        rIntramoddist = -1;
                    }
                    
                    else{
                        rIntramoddist = (double) oIntramoddist / (double) mIntramoddist;
                    }
                    System.out.println("The relative intra mod dist " + oIntramoddist + " / " + mIntramoddist + " = " + rIntramoddist);
                    bwrite.write("Relative intra module distance of module: " + rIntramoddist + "\n");
                    if (getCorrectness(ontologyOWL, moduleOWL) == true) {
                        bwrite.write("Correctness of module: True, the module is logically correct, no new axioms have been added to the ontology.\n");
                    } else {
                        bwrite.write("Correctness of module: False, the module is not logically correct. The following axiom exists in the module but not in the original ontology: \n" + cAx + " \n");
                    }

                    if (getCompleteness(ontologyOWL, moduleOWL) == true) {
                        bwrite.write("Completeness of module: True, the module is logically complete. The meaning of every entity is preserved as in the source ontology. \n");
                    } else {
                        bwrite.write("Completeness of module: False, the module is not logically complete. The meaning of the entity: " + key + " is not preserved in the module as it is in the source ontology. \n");
                    }

                }
                System.out.println("Processed relative metrics for " + module.getName());
                long mEndTime = System.currentTimeMillis();
                long diffTime = mEndTime - mTime;
                //timet= timet/1000;
                double mSeconds = (double) diffTime / 1000d;
                double mMinutes = (double) mSeconds / 60d;
                double mHours = (double) mMinutes / 60d;

                sumMS += mSeconds;
                bwrite.write("Time taken for processing: " + (mSeconds) + " seconds, " + mMinutes + " minutes, " + mHours + " hours.\n");
                bwrite.close();
                jTextArea1.append("Metrics saved to " + logfile2.getAbsolutePath() + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                System.out.println();
            } catch (Exception e) {

            }
        }

        if (mSetLoaded == true) {

            long mTime = System.currentTimeMillis();
            Set<OWLOntology> temp = new HashSet();
            for (int i = 0; i < moduleSet.length; i++) {
                if (!(moduleSet[i] == null)) {

                    temp.add(moduleSet[i]);
                }
            }
            for (int i = 0; i < moduleSet.length; i++) {
                if (!(moduleSet[i] == null)) {
                    System.out.println("Metrics for " + moduleSet[i]);
                    try {
                        jTextArea1.append("Processing metrics for " + moduleSetF[i].getName() + " ..... \n");
                        jTextArea1.update(jTextArea1.getGraphics());
                        logfileSet[i] = new File(System.getProperty("user.home") + "/Logs/" + moduleSetF[i].getName() + "-" + time.format(date).replaceAll(" ", "").replaceAll(":", ".") + ".txt");
                        //    System.out.println("here "+i);
                       // System.out.println("path " + logfileSet[i].toPath());
                        logfileSet[i].getParentFile().mkdirs();

                        FileWriter write = new FileWriter(logfileSet[i]);
                        BufferedWriter bwrite = new BufferedWriter(write);
                        bwrite.write("Log file for " + moduleSetF[i].getName() + "\n\n");

                        redundancy = getRedundancy(moduleSet);
                        temp.remove(moduleSet[i]);
                        encapsulation = getEncap(moduleSet[i], temp);
                        coupling = getCoupling(moduleSet[i], temp);
                        independence = getIndependence(encapsulation, coupling);
                        // getIndependence(encap,)
                        temp.add(moduleSet[i]);
                        //System.out.println("tjios00");

                       // getInterModDistance(temp);

                        /* can't be done because links are in E-coneections which are not recognised by OWLAPI*/
                        //  getInterModDistance(temp);
                        classSet[i] = moduleSet[i].getClassesInSignature(true).size();
                        dpSet[i] = moduleSet[i].getDataPropertiesInSignature(true).size();
                        opSet[i] = moduleSet[i].getObjectPropertiesInSignature(true).size();
                        indSet[i] = moduleSet[i].getIndividualsInSignature(true).size();
                        sizeSet[i] = classSet[i] + dpSet[i] + opSet[i] + indSet[i];
                        axSet[i] = moduleSet[i].getAxiomCount();
                        
                        
            Set <OWLOntology> ontos = moduleSet[i].getDirectImports();
            
            for(OWLOntology ms: ontos){
                axSet[i] += ms.getAxiomCount();
            }

            
           
            
                        appSet[i] = getAppropriateness(axSet[i]);
                        intramoddistSet[i] = intraModuleDistance(moduleSet[i]);
                        attRichSet[i] = getAttributeRichness(moduleSet[i]); //(double) dpSet[i] / classSet[i];
                        inhRichSet[i] = getInheritanceRichness(moduleSet[i]);
                         int newSize = moduleSet[i].getClassesInSignature(true).size()+moduleSet[i].getObjectPropertiesInSignature(true).size()+
                 moduleSet[i].getDataPropertiesInSignature(true).size();
                        cohesionSet[i] = getCohesion(moduleSet[i], newSize);
                        atomicSizeSet[i] = getAtomicSize(moduleSet[i]);

                        bwrite.write("\nMetrics for " + moduleSetF[i].getName() + "\n\n");
                        bwrite.write("No. of classes in ontology: " + classSet[i] + "\n");
                        bwrite.write("No. of OP in ontology: " + opSet[i] + "\n");
                        bwrite.write("No. of DP in ontology: " + dpSet[i] + "\n");
                        bwrite.write("No. of Ind in ontology: " + indSet[i] + "\n");
                        bwrite.write("Size of ontology: " + sizeSet[i] + "\n");
                        bwrite.write("Atomic size of module: " + atomicSizeSet[i] + "\n");
                        bwrite.write("No. of axioms in ontology: " + axSet[i] + "\n");
                        bwrite.write("Appropriateness of ontology: " + appSet[i] + "\n");
                        bwrite.write("Intra module distance: " + intramoddistSet[i] + "\n");

                        bwrite.write("Cohesion of ontology: " + cohesionSet[i] + "\n");

                        bwrite.write("Attribute richness of ontology: " + attRichSet[i] + "\n");
                        //System.out.prinltn(""+moduleOWL.getAxioms().toString()+"\n");
                        bwrite.write("Inheritance richness of ontology: " + inhRichSet[i] + "\n");

                        bwrite.write("Encapsulation of ontology: " + encapsulation + " \n");
                        bwrite.write("Coupling of ontology: " + coupling + " \n");

                        bwrite.write("Is the ontology independent?: " + independence + " \n");

                        bwrite.write("Redundancy of ontology set: " + redundancy + " \n");

                        getRelationshipRichness(moduleSet[i]);

                        if (oLoaded == true) {
                            jTextArea1.append("Processing relative metrics for " + moduleSetF[i].getName() + " ..... \n");
                            jTextArea1.update(jTextArea1.getGraphics());
                            System.out.println("Relative metrics for " + moduleSetF[i]);

                            bwrite.write("Log file for relative metrics for " + moduleSetF[i].getName() + "\n\n");

                            rSizeSet[i] = (double) sizeSet[i] / oSize;

                //System.out.println("s " + sizeSet[i]+" s "+oSize);
                            // System.out.println(mSize + "   ff " + oSize);
                            bwrite.write("\nRelative Metrics for " + moduleSetF[i].getName() + " compared to " + ontology.getName() + "\n\n");
                            bwrite.write("Relative size of module: " + rSizeSet[i] + "\n");

                            
                            Set<OWLClass> someClasses = moduleSet[i].getClassesInSignature(true);
                            Set<OWLObjectProperty> someOp = moduleSet[i].getObjectPropertiesInSignature(true);
                            Set<OWLDataProperty> someDp = moduleSet[i].getDataPropertiesInSignature(true);
                            oIntramoddist = intraModuleDistance(ontologyOWL, someClasses, someOp, someDp);
                
                            rIntramoddist = (double) oIntramoddist / intramoddistSet[i];
                            bwrite.write("Relative intra module distance of module: " + rIntramoddist + "\n");
                            System.out.println("RIMD "+rIntramoddist);
                            if (getCorrectness(ontologyOWL, moduleSet[i]) == true) {
                            bwrite.write("Correctness of module: True, the module is logically correct, no new axioms have been added to the ontology.\n");
                        } else {
                            bwrite.write("Correctness of module: False, the module is not logically correct. The following axiom exists in the module but not in the original ontology: \n" + cAx + " \n");
                        }

                        if (getCompleteness(ontologyOWL, moduleSet[i]) == true) {
                            bwrite.write("Completeness of ontology: True, the module is logically complete. The meaning of every entity is preserved as in the source ontology. \n");
                        } else {
                            bwrite.write("Completeness of ontology: False, the module is not logically complete. The meaning of the entity: " + key + " is not preserved in the module as it is in the source ontology. \n");
                        }

                            System.out.println("Processed relative metrics for " + moduleSetF[i].getName());

                        //bwrite.close();
                        }

                        long mEndTime = System.currentTimeMillis();
                        long diffTime = mEndTime - mTime;
                        //timet= timet/1000;
                        double mSeconds = (double) diffTime / 1000d;
                        double mMinutes = (double) mSeconds / 60d;
                        double mHours = (double) mMinutes / 60d;
                        sumMS += mSeconds;
                        bwrite.write("Time taken for processing: " + (mSeconds) + " seconds, " + mMinutes + " minutes, " + mHours + " hours.\n");
                        bwrite.close();
                        jTextArea1.append("Metrics saved to " + logfileSet[i].getAbsolutePath() + "\n");
                        jTextArea1.update(jTextArea1.getGraphics());
                        System.out.println();
                    } catch (Exception e) {

                    }
                }
            }
        }

        jButton1.setEnabled(false);

        //timet= timet/1000;
        double seconds = (double) sumMS;
        double minutes = (double) seconds / 60d;
        double hours = (double) minutes / 60d;

        jTextArea1.append("\nTime taken for processing:\n" + seconds + " seconds.\n" + minutes + " minutes.\n" + hours + " hours.");
        jTextArea1.update(jTextArea1.getGraphics());
    }
        catch(Exception e){
            System.out.println(e.toString());
            jTextArea1.append("Error "+e.toString());
            jTextArea1.update(jTextArea1.getGraphics());
        }
    } 
    
   //Set <OWLEntity> set1;
    //  Set <OWLEntity> set2;

    //boolean comp =true;
    public double getAtomicSize(OWLOntology onto) {
        double logicalAx = onto.getLogicalAxiomCount();
        double noClass = onto.getClassesInSignature(true).size();
        double noOP = onto.getObjectPropertiesInSignature(true).size();
        double noDP = onto.getDataPropertiesInSignature(true).size();
        double noInd = onto.getIndividualsInSignature(true).size();
        double noEnt = noClass + noOP + noDP + noInd;

        Set<OWLClass> classes = onto.getClassesInSignature(true);
        Set<OWLObjectProperty> op = onto.getObjectPropertiesInSignature(true);
        Set<OWLDataProperty> dp = onto.getDataPropertiesInSignature(true);
        Set<OWLNamedIndividual> indi = onto.getIndividualsInSignature(true);

        double sum = 0;
        for (OWLClass c : classes) {

            // System.out.println(" class "+c);
            Set<OWLAxiom> ax = c.getReferencingAxioms(onto, true);
            sum += ax.size();
            for (OWLAxiom a : ax) {
                //System.out.println(a);
            }

            // System.out.println();
        }

        for (OWLObjectProperty o : op) {
            Set<OWLAxiom> ax = o.getReferencingAxioms(onto, true);
            sum += ax.size();
        }

        for (OWLDataProperty d : dp) {
            Set<OWLAxiom> ax = d.getReferencingAxioms(onto, true);
            sum += ax.size();
        }

        for (OWLNamedIndividual i : indi) {
            Set<OWLAxiom> ax = i.getReferencingAxioms(onto, true);
            sum += ax.size();
        }

        //System.out.println("atom "+sum);
        //System.out.println("no. axioms "+logicalAx);
        //System.out.println("no. Ent "+noEnt);
        //System.out.println(sum/noEnt);
        double atomicSize = sum / noEnt;
        System.out.println("The atomic size is " + atomicSize);
        return atomicSize;
    }

    /* can't be done because links are in E-coneections which are not recognised by OWLAPI*/
    /*public double getInterModDistance(Set<OWLOntology> modSet) {

     System.out.println("Intermodule distance method");
     int numEntities = 0;
     int numModules = 0;
        
     Set <OWLOntology> modSet2 =modSet;
        
     Iterator <OWLOntology> it = modSet.iterator();
     OWLOntology temp1;
     OWLOntology temp2;

     while(it.hasNext() ){
     temp1= it.next();
            
            
          
     Set <OWLObjectProperty> op = temp1.getObjectPropertiesInSignature(true);
            
         
     Iterator <OWLOntology> it2 = modSet2.iterator();
     while(it2.hasNext()  ){
     temp2 = it2.next();
     if( !(temp1.equals(temp2)) ){
                
     }
     }
     System.out.println();
     }
     /*
     for (OWLOntology m : modSet) {
     OWLOntology n = modSet.iterator().next();
     Set<OWLOntology> non = modSet;

     numEntities += m.getClassesInSignature(true).size() + m.getDataPropertiesInSignature(true).size() + m.getObjectPropertiesInSignature(true).size() + m.getIndividualsInSignature(true).size();

     Set<OWLEntity> eSet = m.getSignature();

     for (OWLEntity e : eSet) {

     }
     }

     return 0;
     }*/
    public boolean getIndependence(double encap, double coup) {
        boolean ind;
        if ((encap == 1) && (coup == 0)) {
            ind = true;
        } else {
            ind = false;
        }
        System.out.println("Independent? " + ind);
        return ind;
    }

    public double getCoupling(OWLOntology mod, Set<OWLOntology> modSet) {
        double coup = 0;

        // Set <OWLAxiom> axiomSet = mod.getAxioms();
        Set<OWLClass> classSet = mod.getClassesInSignature(true);
        Set<OWLObjectProperty> opSet = mod.getObjectPropertiesInSignature(true);

        for (OWLOntology m : modSet) {
            double prod = m.getClassesInSignature(true).size() + m.getObjectPropertiesInSignature(true).size() + m.getDataPropertiesInSignature(true).size() + m.getIndividualsInSignature(true).size();
            prod = prod * mod.getClassesInSignature(true).size() + mod.getObjectPropertiesInSignature(true).size() + mod.getDataPropertiesInSignature(true).size() + mod.getIndividualsInSignature(true).size();
            //  System.out.println("prod "+prod);
            double extLink = 0;
            Set<OWLClass> classSet2 = m.getClassesInSignature(true);
            Set<OWLObjectProperty> opSet2 = m.getObjectPropertiesInSignature(true);
            // System.out.println("modul "+m.toString());
            for (OWLClass c : classSet) {
                Set<OWLIndividual> indSet = c.getIndividuals(mod);
                // System.out.println("c "+c);
                if (c.toString().equals("owl:ForeignClass")) {

                    for (OWLClass c2 : classSet2) {
                        //System.out.println("c2 "+c2);
                        for (OWLIndividual ind : indSet) {
                            //    System.out.println("ind "+ind);
                            if (c2.toString().equals(ind.toString())) {
                                //System.out.println("equal");
                                extLink++;
                            }
                        }
                    }
                }
                if (c.toString().equals("owl:ForeignLinkProperty")) {
                    //  System.out.println("yes " +opSet2.size());
                    for (OWLObjectProperty op2 : opSet2) {
                        //   System.out.println("op2 "+op2);
                        for (OWLIndividual ind : indSet) {
                            //   System.out.println("ind "+ind);
                            if (op2.toString().equals(ind.toString())) {
                                // System.out.println("equal2");
                                extLink++;
                            }
                        }
                    }
                }

                //  System.out.println("ext "+extLink);
            }

            coup += extLink / prod;
            //System.out.println("coup "+coup);
        }
        System.out.println("The coupling is " + coup);
        return coup;
    }

    public double getEncap(OWLOntology mod, Set<OWLOntology> modSet) {

        Set<OWLAxiom> set1 = mod.getAxioms();
        double encap = 0;
        double t = 0;
        for (OWLOntology m : modSet) {
            double some = 0;
            double count = 0;

            Set<OWLAxiom> temp = m.getAxioms();

            //int encap =0;
            for (OWLAxiom ax : set1) {
                // System.out.println("ax2 "+ax);
                if (temp.contains(ax)) {
                    count++;
                    //System.out.println(count);
                    //  System.out.println(mod+" contains axiom "+ax);
                }

                //  System.out.println("e1 "+encap);
            }
            // some+=count;
            t += count / set1.size();
            //      System.out.println("t "+t+" "+count+" "+set1.size());
        }
        encap = 1 - (t / (modSet.size() + 1));
        //System.out.println("e2 "+encap);
        System.out.println("The encapsulation is " + encap);
        return encap;
    }

    private boolean getCompleteness(OWLOntology ont, OWLOntology mod) {
        boolean comp = true;
        Set<OWLEntity> entOnt = ont.getSignature();
        Set<OWLEntity> entMod = mod.getSignature();

        for (OWLEntity a : entOnt) {
            for (OWLEntity b : entMod) {
                if (a.equals(b)) {
                    if (a.isOWLClass()) {
                        // if(a.equals(b)){
                        Set<OWLClassAxiom> s1 = ont.getAxioms(a.asOWLClass());
                        Set<OWLClassAxiom> s2 = mod.getAxioms(b.asOWLClass());
                        if (!(s1.equals(s2))) {
                            comp = false;
                            key = a;
                            break;
                        }
                        // }
                    }

                    if (a.isOWLObjectProperty()) {
                        // if(a.equals(b)){
                        Set<OWLObjectPropertyAxiom> s1 = ont.getAxioms(a.asOWLObjectProperty());
                        Set<OWLObjectPropertyAxiom> s2 = mod.getAxioms(b.asOWLObjectProperty());
                        if (!(s1.equals(s2))) {
                            comp = false;
                            key = a;
                            break;
                        }
                    } //}
                    else if (a.isOWLDataProperty()) {
                        //if(a.equals(b)){
                        Set<OWLDataPropertyAxiom> s1 = ont.getAxioms(a.asOWLDataProperty());
                        Set<OWLDataPropertyAxiom> s2 = mod.getAxioms(b.asOWLDataProperty());
                        if (!(s1.equals(s2))) {
                            comp = false;
                            key = a;
                            break;
                        }
                        //}
                    } else if (a.isOWLNamedIndividual()) {
                        // if(a.equals(b)){
                        Set<OWLIndividualAxiom> s1 = ont.getAxioms(a.asOWLNamedIndividual());
                        Set<OWLIndividualAxiom> s2 = mod.getAxioms(b.asOWLNamedIndividual());
                        if (!(s1.equals(s2))) {
                            comp = false;
                            key = a;
                            break;
                            //   }
                        }
                    } else if (a.isOWLAnnotationProperty()) {
                        //if(a.equals(b)){
                        Set<OWLAnnotationAxiom> s1 = ont.getAxioms(a.asOWLAnnotationProperty());
                        Set<OWLAnnotationAxiom> s2 = mod.getAxioms(b.asOWLAnnotationProperty());
                        if (!(s1.equals(s2))) {
                            comp = false;
                            key = a;
                            break;
                            //   }
                        }
                    }
                }
            }
        }

        System.out.println("Complete ? " + comp);
        return comp;
    }

    OWLAxiom cAx;

    private boolean getCorrectness(OWLOntology ont, OWLOntology mod) {
        Set<OWLAxiom> mAxioms = mod.getAxioms();
        //  Set <OWLAxiom> oAxioms = ont.getAxioms(); 
        boolean corr = true;
        for (OWLAxiom m : mAxioms) {
            if (!(ont.containsAxiom(m))) {
                corr = false;
                //   System.out.println(ont+" does not contain axiom "+m);
                cAx = m;
                break;
            }

        }
        System.out.println("Correct ? " + corr);
        return corr;
    }

    public double getAppropriateness(int numAxioms) {
        double val = 0;
        if (numAxioms > 0 && numAxioms < 500) {
            double cos = Math.cos(numAxioms * (Math.PI / 250));

            //  Fraction f1 =new Fraction(1,2);
            //  double ii = (.5) * (cos);
            val = (.5) - ((.5) * cos);
        } else {
            val = -1;
        }
        System.out.println("Appropriateness " + val);
        return val;
    }

    public double getRelationshipRichness(OWLOntology onto) {

        return 0;
    }

    public double getInheritanceRichness(OWLOntology onto) {
        Set<OWLClass> classes = onto.getClassesInSignature(true);

        double agg = 0;
        int parentclasses=0;
        for (OWLClass c : classes) {
            int countsub = 0;
            countsub = c.getSubClasses(onto).size();
            
            
            if (countsub > 0) {
               // agg += (1 / countsub);
                 agg += countsub;
                 parentclasses++;
            }
        }
        // System.out.println("IR " + agg + " " + classes.size());
        //double ir = (double) (agg / classes.size());
        double ir = (double) (agg / parentclasses);
        System.out.println("The inheritance richness is " + ir);
        return ir;
    }
    
    public double getAttributeRichness(OWLOntology onto){
        Set<OWLClass> classes = onto.getClassesInSignature(true);
        
        int attributes= 0;
        double ar = 0;
         for (OWLClass c : classes) {
            // System.out.println("classes "+c);
            Set <OWLAxiom> axSet = c.getReferencingAxioms(onto, true);
            
             //System.out.println("class "+c);
          // System.out.println("set "+moduleOWL.getAxioms(c));
          //  System.out.println();
            
            for(OWLAxiom a: axSet){
               // System.out.println("ax "+a);
               if( (!(a.getAxiomType()== AxiomType.OBJECT_PROPERTY_DOMAIN)) && (!(a.getAxiomType()== AxiomType.OBJECT_PROPERTY_RANGE)) 
                    &&   (!(a.getAxiomType()== AxiomType.DATA_PROPERTY_DOMAIN)) && (!(a.getAxiomType()== AxiomType.DATA_PROPERTY_RANGE))
                       ){
                Set <OWLObjectProperty> op = a.getObjectPropertiesInSignature();
                Set <OWLDataProperty> dp = a.getDataPropertiesInSignature();
                attributes+=op.size();
                attributes+=dp.size();
               // System.out.println("op "+op);
               //   System.out.println("dp "+dp);
            }
            }
            //System.out.println(axSet.toString());
          // Set <OWLObjectProperty> op = c.getObjectPropertiesInSignature();
          // Set <OWLDataProperty> dp = c.getDataPropertiesInSignature();
           
          // System.out.println("OP in sig "+op.toString());
          // System.out.println("DP in sig "+dp.toString());
        }
        ar = (double) attributes/ (double)classes.size();
       // System.out.println("att "+attributes);
        //System.out.println("class "+classes.size());
        System.out.println("The attribute richness is "+ar+"); // att size" +attributes+"cl size"+classes.size());
        return ar;
        
        
    }

    // int jumps = 0;
    public double getCohesion(OWLOntology onto, int size) {
       // double coh = 0;
        /*Set<OWLClass> classes = onto.getClassesInSignature(true);
        OWLClass[] classArray = new OWLClass[500000];
        int k = 0;

        for (OWLClass a : classes) {
            classArray[k] = a;
            k++;
        }
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   System.out.println(i+" - "+j);
                // System.out.println("farness "+bfsFarness(classArray[i],classArray[j], onto)+"-- coh "+(1/(bfsFarness(classArray[i],classArray[j], onto))));
                if (!(bfsFarness(classArray[i], classArray[j], onto) == 0)) {
                    coh += (1 / (bfsFarness(classArray[i], classArray[j], onto)));
                }
            }
        }
        
        
        Set <OWLObjectProperty> ops = onto.getObjectPropertiesInSignature(true);
        OWLObjectProperty[] opArray = new OWLObjectProperty[500000];
        k = 0;
        
       
        
            for (OWLObjectProperty a : ops) {
            opArray[k] = a;
            k++;
        }
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   System.out.println(i+" - "+j);
                // System.out.println("farness "+bfsFarness(classArray[i],classArray[j], onto)+"-- coh "+(1/(bfsFarness(classArray[i],classArray[j], onto))));
                if (!(bfsFarnessOP(opArray[i], opArray[j], onto) == 0)) {
                    coh += (1 / (bfsFarnessOP(opArray[i], opArray[j], onto)));
                }
            }
        }
        
         Set <OWLDataProperty> dps = onto.getDataPropertiesInSignature(true);
         OWLDataProperty[] dpArray = new OWLDataProperty[500000];
         k = 0;
         
             for (OWLDataProperty a : dps) {
            dpArray[k] = a;
            k++;
        }
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   System.out.println(i+" - "+j);
                // System.out.println("farness "+bfsFarness(classArray[i],classArray[j], onto)+"-- coh "+(1/(bfsFarness(classArray[i],classArray[j], onto))));
                if (!(bfsFarnessDP(dpArray[i], dpArray[j], onto) == 0)) {
                    coh += (1 / (bfsFarnessDP(dpArray[i], dpArray[j], onto)));
                }
            }
        }
        // System.out.println("numer " + coh);
        // System.out.println("denoom " + (size) * (size - 1));
        coh = coh / ((size) * (size - 1));
*/
        double coh  = tempCoh / ((size) * (size-1));
        System.out.println("The cohesion is " + coh);
        return coh;
    }
    
     public double getCohesionOnt(OWLOntology onto, int size) {
  double coh  = tempCoh2 / ((size) * (size-1));
        System.out.println("The cohesion is " + coh);
        return coh;
    }
    public double intraModuleDistance(OWLOntology onto) {
        tempCoh = 0;
        tempCoh2 = 0;
        double tempVar = 0;
        System.out.println("here");
        double agg = 0;
        Set<OWLClass> classes = onto.getClassesInSignature(true);

        //   Set <OWLObjectProperty> oprops = onto.getObjectPropertiesInSignature();
        // Set <OWLDataProperty> dprops = onto.getDataPropertiesInSignature();
        OWLClass[] classArray = new OWLClass[500000];
        int k = 0;

        ArrayList temp1 = new ArrayList();
        ArrayList temp2 = new ArrayList();
        temp1.add(k);
        temp2.add(k);

        int yy = 0;
        for (OWLClass a : classes) {
            classArray[k] = a;
            k++;
        }
        
        System.out.println("here");
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                tempVar = bfsFarness(classArray[i], classArray[j], onto);
                agg+=tempVar;
               // System.out.println("distance between "+classArray[i]+" and "+classArray[j]);
                if (tempVar == 0){
                    tempCoh+=0;
                }
                
                else{
                    tempCoh+=1/ (tempVar);
                }
                //agg += bfsFarness(classArray[i], classArray[j], onto);
                // System.out.println(" distance between "+classArray[i]+" "+classArray[j]+" is "+agg);
                temp1.add(classArray[i]);
                temp2.add(classArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }
        
        System.out.println("Done class distances");
        
         Set<OWLObjectProperty> op = onto.getObjectPropertiesInSignature(true);
         OWLObjectProperty[] opArray = new OWLObjectProperty[500000];
        k = 0;

        ArrayList tempOp1 = new ArrayList();
        ArrayList tempOp2 = new ArrayList();
        tempOp1.add(k);
        tempOp2.add(k);

        yy = 0;
        for (OWLObjectProperty a : op) {
            opArray[k] = a;
            k++;
        }
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                // System.out.println("farness "+classArray[i]+" "+classArray[j]);
               // agg += bfsFarnessOP(opArray[i], opArray[j], onto);

                
                tempVar = bfsFarnessOP(opArray[i],opArray[j], onto);
                agg+=tempVar;
             //   System.out.println("distance between "+opArray[i]+" and "+opArray[j]);
                if (tempVar == 0){
                    tempCoh+=0;
                }
                
                else{
                    tempCoh+=1/ (tempVar);
                }
                
                tempOp1.add(opArray[i]);
                tempOp2.add(opArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }
        System.out.println("Done OP distances");
        Set<OWLDataProperty> dp2 = onto.getDataPropertiesInSignature(true);
        OWLDataProperty[] dpArray = new OWLDataProperty[500000];
        k = 0;

        ArrayList tempDp1 = new ArrayList();
        ArrayList tempDp2 = new ArrayList();
        tempDp1.add(k);
        tempDp2.add(k);

        yy = 0;
        for (OWLDataProperty a : dp2) {
            dpArray[k] = a;
            k++;
        }
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                // System.out.println("farness "+classArray[i]+" "+classArray[j]);
              //  agg += bfsFarnessDP(dpArray[i], dpArray[j], onto);

                
                tempVar = bfsFarnessDP(dpArray[i], dpArray[j], onto);
                agg+=tempVar;
             //   System.out.println("distance between "+dpArray[i]+" and "+dpArray[j]);
                if (tempVar == 0){
                    tempCoh+=0;
                }
                
                else{
                    tempCoh+=1/ (tempVar);
                }
                tempDp1.add(dpArray[i]);
                tempDp2.add(dpArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }

        System.out.println("Done DP distances");
        System.out.println("The intramodule distance is " + agg);
        return agg;
    }

    public double intraModuleDistance(OWLOntology onto, Set<OWLClass> classes, Set<OWLObjectProperty> op, Set<OWLDataProperty> dp) {
        //tempCoh = 0;
        tempCoh2 = 0;
        double agg = 0;
        double tempVar = 0;
        Set<OWLClass> classes2 = onto.getClassesInSignature(true);
        Set<OWLClass> newSet = new HashSet();
        
      //  System.out.println("classes "+classes.toString());
// System.out.println("classes2 "+classes2.toString());
        String tempstring= "";
        String tempstring2 = "";
        for (OWLClass c2 : classes2) {
             if(c2.toString().contains("#")){
            tempstring2 = c2.toString().substring(c2.toString().indexOf("#"));
           // System.out.println(tempstring2+" ts2");
             }
            
           for (OWLClass c1 : classes) {
               if(c1.toString().contains("#")){
                  tempstring = c1.toString().substring(c1.toString().indexOf("#"));
                  //  System.out.println(tempstring+" ts");
               }
               
               if((tempstring.equals(tempstring2))&& (!(tempstring.isEmpty())) ){
                    newSet.add(c2);
               }
               
               else{
                   newSet.add(c1);
               }
           }
        }
   
        OWLClass[] classArray = new OWLClass[500000];
        int k = 0;

        ArrayList temp1 = new ArrayList();
        ArrayList temp2 = new ArrayList();
        temp1.add(k);
        temp2.add(k);

        int yy = 0;
        for (OWLClass a : newSet) {
            classArray[k] = a;
            //System.out.println(classArray[k]);
            k++;
        }
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                // System.out.println("farness "+classArray[i]+" "+classArray[j]);
                //agg += bfsFarness(classArray[i], classArray[j], onto);
//java heapspace
                
                tempVar = bfsFarness(classArray[i], classArray[j], onto);
                //System.out.println("check "+classArray[i]+" "+classArray[j]);
                agg+=tempVar;
                //System.out.println("distance between "+classArray[i]+" and "+classArray[j]);
                if (tempVar == 0){
                    tempCoh2+=0;
                }
                
                else{
                    tempCoh2+=1/ (tempVar);
                }
                
                temp1.add(classArray[i]);
                temp2.add(classArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }
        System.out.println("Done class distances");
        Set<OWLObjectProperty> op2 = onto.getObjectPropertiesInSignature(true);
        Set<OWLObjectProperty> newSetOp = new HashSet();

        String tempstringOp= "";
        String tempstringOp2 = "";
        for (OWLObjectProperty o2 : op2) {
             if(o2.toString().contains("#")){
            tempstringOp2 = o2.toString().substring(o2.toString().indexOf("#"));
             }
            
           for (OWLObjectProperty o1 : op) {
               if(o1.toString().contains("#")){
                  tempstringOp = o1.toString().substring(o1.toString().indexOf("#"));
               }
               
               if((tempstringOp.equals(tempstringOp2))&& (!(tempstringOp.isEmpty())) ){
                    newSetOp.add(o2);
               }
           }
        }
   
        OWLObjectProperty[] opArray = new OWLObjectProperty[500000];
        k = 0;

        ArrayList tempOp1 = new ArrayList();
        ArrayList tempOp2 = new ArrayList();
        tempOp1.add(k);
        tempOp2.add(k);

        yy = 0;
        for (OWLObjectProperty a : op2) {
            opArray[k] = a;
            k++;
        }
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                // System.out.println("farness "+classArray[i]+" "+classArray[j]);
                //agg += bfsFarnessOP(opArray[i], opArray[j], onto);

                
                tempVar = bfsFarnessOP(opArray[i], opArray[j], onto);
                agg+=tempVar;
               // System.out.println("distance between "+opArray[i]+" and "+opArray[j]);
                if (tempVar == 0){
                    tempCoh2+=0;
                }
                
                else{
                    tempCoh2+=1/ (tempVar);
                }
                
                
                tempOp1.add(opArray[i]);
                tempOp2.add(opArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }
        
        System.out.println("Done OP distances");
         Set<OWLDataProperty> dp2 = onto.getDataPropertiesInSignature(true);
        Set<OWLDataProperty> newSetDp = new HashSet();

        String tempstringDp= "";
        String tempstringDp2 = "";
        for (OWLDataProperty d2 : dp2) {
             if(d2.toString().contains("#")){
            tempstringDp2 = d2.toString().substring(d2.toString().indexOf("#"));
             }
            
           for (OWLDataProperty d1 : dp) {
               if(d1.toString().contains("#")){
                  tempstringDp = d1.toString().substring(d1.toString().indexOf("#"));
               }
               
               if((tempstringDp.equals(tempstringDp2))&& (!(tempstringDp.isEmpty())) ){
                    newSetDp.add(d2);
               }
           }
        }
   
        OWLDataProperty[] dpArray = new OWLDataProperty[500000];
        k = 0;

        ArrayList tempDp1 = new ArrayList();
        ArrayList tempDp2 = new ArrayList();
        tempDp1.add(k);
        tempDp2.add(k);

        yy = 0;
        for (OWLDataProperty a : dp2) {
            dpArray[k] = a;
            k++;
        }
        // System.out.println("check");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {

                //   if( (temp1.contains(classArray[i]) && temp2.contains(classArray[j])) && (temp1.indexOf(classArray[i]) == temp2.indexOf(classArray[j]))  ){
                //  }
                //   else{
                //  System.out.println(i+" - "+j);
                //     System.out.println("index "+temp1.indexOf(classArray[i])+"  dd"+temp2.indexOf(classArray[j]));
                // agg += bfsFarness(classArray[i], classArray[j], onto) / 2;
                // System.out.println("farness "+classArray[i]+" "+classArray[j]);
              //  agg += bfsFarnessDP(dpArray[i], dpArray[j], onto);
tempVar = bfsFarnessDP(dpArray[i], dpArray[j], onto);
                agg+=tempVar;
               // System.out.println("distance between "+dpArray[i]+" and "+dpArray[j]);
                if (tempVar == 0){
                    tempCoh2+=0;
                }
                
                else{
                    tempCoh2+=1/ (tempVar);
                }
                
                tempDp1.add(dpArray[i]);
                tempDp2.add(dpArray[j]);
                //System.out.println(yy+" - "+classArray[i]+"  "+classArray[j]);
                yy++;
                //System.out.println(i+" "+j);
                //  }

            }
        }
        System.out.println("Done DP distances");
        System.out.println("The intramodule distance for the original ontology is " + agg);
        return agg;
    }

    public double bfsFarnessOP(OWLObjectProperty a, OWLObjectProperty b, OWLOntology onto) {

        Set<OWLObjectProperty> visited = new HashSet();
        Set<OWLObjectProperty> current = new HashSet();
        Set<OWLObjectProperty> temp = new HashSet();
        current.add(a);
        double jumps = 0;
        // System.out.println();
        boolean f = false;
        //System.out.println("Hops : "+jumps);
        //         System.out.println("visited "+visited.toString());
        //      System.out.println("current "+current.toString());
        //  System.out.println("temp "+temp.toString());

        while ((!current.contains(b))) {
            f = false;

            // System.out.println(jumps);
            //for(OWLClass cclass: current){
            visited.addAll(current);
            temp.clear();
            temp.addAll(current);
            current.clear();
            for (OWLObjectProperty op : temp) {
                Set<OWLObjectPropertyExpression> sub = op.getSubProperties(onto);
                // System.out.println("cclass "+cclass);
                for (OWLObjectPropertyExpression s1 : sub) {
                    if(!(s1.isAnonymous())){
                    if ((!(visited.contains(s1.asOWLObjectProperty())))) {
                        //   System.out.println("s1 "+s1+s1.asOWLClass());
                        current.add(s1.asOWLObjectProperty());
                    }
                    }
                }

                Set<OWLObjectPropertyExpression> sup = op.getSuperProperties(onto);
                for (OWLObjectPropertyExpression s2 : sup) {
                    if(!(s2.isAnonymous())){
                        
                    if ((!(visited.contains(s2.asOWLObjectProperty())))) {
                        //System.out.println("s2 "+s2+ s2.asOWLClass());
                        current.add(s2.asOWLObjectProperty());
                    }
                }
                }

            }
            //   
            // System.out.println();
             /*
             for(OWLClass v: visited){
             if(current.contains(v)){
             f=true;  
             }
             }
             if(f==false){*/
            jumps++;
            //  System.out.println("Hops : "+jumps);
            // System.out.println("visited "+visited.toString());
            //System.out.println("current "+current.toString());
            //  System.out.println("temp "+temp.toString());

          //  System.out.println("OP jumps "+jumps);
            if (jumps > 0 && current.isEmpty()) {

                jumps = 0;
                break;
            }

            //  }
        }
     //  System.out.println();

        // System.out.println("newcurrent "+current.toString());
        // System.out.println("Distance between "+a+"  and "+b+" is: "+jumps+" ."); 
        // System.out.println("Farness between "+a+"  and "+b+" is: "+jumps/2+" .");  
        return jumps;

    }

    public double bfsFarnessDP(OWLDataProperty a, OWLDataProperty b, OWLOntology onto) {

        Set<OWLDataProperty> visited = new HashSet();
        Set<OWLDataProperty> current = new HashSet();
        Set<OWLDataProperty> temp = new HashSet();
        current.add(a);
        double jumps = 0;
        // System.out.println();
        boolean f = false;
        //System.out.println("Hops : "+jumps);
        //         System.out.println("visited "+visited.toString());
        //      System.out.println("current "+current.toString());
        //  System.out.println("temp "+temp.toString());

        while ((!current.contains(b))) {
            f = false;

            // System.out.println(jumps);
            //for(OWLClass cclass: current){
            visited.addAll(current);
            temp.clear();
            temp.addAll(current);
            current.clear();
            for (OWLDataProperty op : temp) {
                Set<OWLDataPropertyExpression> sub = op.getSubProperties(onto);
                // System.out.println("cclass "+cclass);
                for (OWLDataPropertyExpression s1 : sub) {
                    if ((!(visited.contains(s1.asOWLDataProperty())))) {
                        //   System.out.println("s1 "+s1+s1.asOWLClass());
                        current.add(s1.asOWLDataProperty());
                    }

                }

                Set<OWLDataPropertyExpression> sup = op.getSuperProperties(onto);
                for (OWLDataPropertyExpression s2 : sup) {
                    if ((!(visited.contains(s2.asOWLDataProperty())))) {
                        //System.out.println("s2 "+s2+ s2.asOWLClass());
                        current.add(s2.asOWLDataProperty());
                    }
                }

            }
            //   
            // System.out.println();
             /*
             for(OWLClass v: visited){
             if(current.contains(v)){
             f=true;  
             }
             }
             if(f==false){*/
            jumps++;
            //  System.out.println("Hops : "+jumps);
            // System.out.println("visited "+visited.toString());
            //System.out.println("current "+current.toString());
            //  System.out.println("temp "+temp.toString());
//System.out.println("DP jumps "+jumps);
            if (jumps > 0 && current.isEmpty()) {

                jumps = 0;
                break;
            }

            //  }
        }
     //  System.out.println();

        // System.out.println("newcurrent "+current.toString());
        // System.out.println("Distance between "+a+"  and "+b+" is: "+jumps+" ."); 
        // System.out.println("Farness between "+a+"  and "+b+" is: "+jumps/2+" .");  
        return jumps;

    }
    
   

    public double bfsFarness(OWLClass a, OWLClass b, OWLOntology onto) {

        //System.out.println("class a "+a);
       // System.out.println("class b "+b);
       // System.out.println("in here ");
        Set<OWLClass> visited = new HashSet();
        Set<OWLClass> current = new HashSet();
        Set<OWLClass> temp = new HashSet();

        current.add(a);
        double jumps = 0;
      //  System.out.println("ab  " + a + " " + b);
        boolean f = false;
        //System.out.println("Hops : "+jumps);
        //         System.out.println("visited "+visited.toString());
        //      System.out.println("current "+current.toString());
        //  System.out.println("temp "+temp.toString());

        while ((!current.contains(b))) {
            f = false;

            // System.out.println(jumps);
            //for(OWLClass cclass: current){
            visited.addAll(current);
            temp.clear();
            temp.addAll(current);
            current.clear();
            int ind = 1;
            for (OWLClass cclass : temp) {
               // 
               // System.out.println(ind+") cclass "+cclass);
                ind++;
                Set<OWLClassExpression> sub = cclass.getSubClasses(onto);

                //   System.out.println("cclass "+cclass+" sub "+sub.toString());
                for (OWLClassExpression s1 : sub) {
                    //    System.out.println("sub s1 "+s1);
                    if (s1.getClassExpressionType() == ClassExpressionType.OWL_CLASS && (!(visited.contains(s1.asOWLClass())))) {
                        //       System.out.println("s1 "+s1+s1.asOWLClass());
                        current.add(s1.asOWLClass());

                    }

                }

                Set<OWLClassExpression> sup = cclass.getSuperClasses(onto);
                for (OWLClassExpression s2 : sup) {
                    // System.out.println("sup s2 "+s2);
                    if ((s2.getClassExpressionType() == ClassExpressionType.OWL_CLASS) && (!(visited.contains(s2.asOWLClass())))) {
                        //System.out.println("s2 "+s2+ s2.asOWLClass());
                        current.add(s2.asOWLClass());

                    }
                }

                Set<OWLClassExpression> ex = cclass.getEquivalentClasses(onto);
                // OWLEntity foo;
                // Set <OWLEntityEcpress> exdd = cclass.g
                //   if(ex.size() == 1){
                for (OWLClassExpression s3 : sup) {
                    if ((s3.getClassExpressionType() == ClassExpressionType.OWL_CLASS) && (!(visited.contains(s3.asOWLClass())))) {
                        //System.out.println("s2 "+s2+ s2.asOWLClass());
                        current.add(s3.asOWLClass());
                        // System.out.println("eq "+cclass+ " and "+s3+" and "+s3.asOWLClass());

                    }
                    // }
                }

            }
            //   
            // System.out.println();
             /*
             for(OWLClass v: visited){
             if(current.contains(v)){
             f=true;  
             }
             }
             if(f==false){*/
            jumps++;
              /*System.out.println("Hops : "+jumps);
             System.out.println("visited "+visited.toString());
            System.out.println("current "+current.toString());
              System.out.println("temp "+temp.toString());
            System.out.println("CLASS jumps "+jumps);*/
            if (jumps > 0 && current.isEmpty()) {

                jumps = 0;
                break;
            }

            //  }
        }
     //  System.out.println();

        // System.out.println("newcurrent "+current.toString());
      //  System.out.println("Distance between "+a+"  and "+b+" is: "+jumps+" ."); 
        // System.out.println("Farness between "+a+"  and "+b+" is: "+jumps/2+" .");  
        return jumps;
    }

    public double getRedundancy(OWLOntology[] modSet) {

        double axiomTotal = 0;
        Set<OWLAxiom> nodup = new HashSet();

        int count = 0;
        for (OWLOntology modSet1 : modSet) {
            if (!(modSet1 == null)) {
                //System.out.println("COUNT " + count);
                axiomTotal += modSet1.getAxiomCount();
                Set<OWLOntology> ontos = modSet1.getDirectImports();
                nodup.addAll(modSet1.getAxioms());
                for (OWLOntology o:ontos){
                   axiomTotal += o.getAxiomCount(); 
                   nodup.addAll(o.getAxioms());
                }
                
                
                
               // System.out.println(axiomTotal);
               // System.out.println(nodup.size());
                count++;
            }

        }

        double axiomDist = nodup.size();

        double red = (axiomTotal - axiomDist) / axiomTotal;
        NumberFormat formatter = new DecimalFormat("0.00000000000");
        String string = formatter.format(red);
        System.out.println("The redundancy of the set: " + red);
        System.out.println("The redundancy of the set: " + string);
        return red;
    }

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

      /*   Create and display the form */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new start().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JFileChooser jFileChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
       new start().setVisible(true);
       
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }
}
