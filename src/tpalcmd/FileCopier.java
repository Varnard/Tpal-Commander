package tpalcmd;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;

public class FileCopier extends JFrame implements ActionListener, PropertyChangeListener
{
    private static final long serialVersionUID = 1L;

    private JTextField txtTarget;
    private JProgressBar progressAll;
    private JProgressBar progressCurrent;
    private JButton btnCopy;
    private CopyTask task;
    private File[] files;
    private int filesCount;
    private String title;
    private String targetLabel;
    private String currentProgressLabel;
    private String allProgressLabel;
    private String copyButton;
    private String cancelButton;    
    

    public FileCopier(File[] files, Locale locale)
    {
    	this.files=files;
    	filesCount=files.length;
    	
    	ResourceBundle rb = ResourceBundle.getBundle("language", locale);
    	title = rb.getString("FCTitle");
    	targetLabel = rb.getString("FCTargetLabel");
    	currentProgressLabel = rb.getString("FCCurrentProgressLabel");
    	allProgressLabel = rb.getString("FCAllProgressLabel");
    	copyButton = rb.getString("FCCopyButton");
    	cancelButton = rb.getString("FCCancelButton");
    	
        buildGUI();
    }

    private void buildGUI()
    {
        setTitle(title);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if(task != null) task.cancel(true);
                dispose();                
            }
        });

        JLabel lblTarget = new JLabel(targetLabel);
        txtTarget = new JTextField(40);
        
        JLabel lblProgressAll = new JLabel(allProgressLabel);
        JLabel lblProgressCurrent = new JLabel(currentProgressLabel);
        progressAll = new JProgressBar(0, 100);
        progressAll.setStringPainted(true);
        progressCurrent = new JProgressBar(0, 100);
        progressCurrent.setStringPainted(true);

        btnCopy = new JButton(copyButton);
          btnCopy.addActionListener(this);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panInputLabels = new JPanel(new BorderLayout(0, 5));
        JPanel panInputFields = new JPanel(new BorderLayout(0, 5));
        JPanel panProgressLabels = new JPanel(new BorderLayout(0, 5));
        JPanel panProgressBars = new JPanel(new BorderLayout(0, 5));

        panInputLabels.add(lblTarget, BorderLayout.CENTER);
        panInputFields.add(txtTarget, BorderLayout.CENTER);
        panProgressLabels.add(lblProgressAll, BorderLayout.NORTH);
        panProgressLabels.add(lblProgressCurrent, BorderLayout.CENTER);
        panProgressBars.add(progressAll, BorderLayout.NORTH);
        panProgressBars.add(progressCurrent, BorderLayout.CENTER);

        JPanel panInput = new JPanel(new BorderLayout(0, 5));
        panInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panProgress = new JPanel(new BorderLayout(0, 5));
        panProgress.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panControls = new JPanel(new BorderLayout());
        panControls.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        panInput.add(panInputLabels, BorderLayout.LINE_START);
        panInput.add(panInputFields, BorderLayout.CENTER);
        panProgress.add(panProgressLabels, BorderLayout.LINE_START);
        panProgress.add(panProgressBars, BorderLayout.CENTER);
        panControls.add(btnCopy, BorderLayout.CENTER);

        JPanel panUpper = new JPanel(new BorderLayout());
        panUpper.add(panInput, BorderLayout.NORTH);
        panUpper.add(panProgress, BorderLayout.SOUTH);

        contentPane.add(panUpper, BorderLayout.NORTH);
        contentPane.add(panControls, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(copyButton.equals(btnCopy.getText()))
        {
            File target = new File(txtTarget.getText());            

            if (filesCount>0)
            {              
            task = this.new CopyTask(files, target);
            task.addPropertyChangeListener(this);
            task.execute();

            btnCopy.setText(cancelButton);
            }
        }
        else if(cancelButton.equals(btnCopy.getText()))
        {
            task.cancel(true);
            dispose();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if("progress".equals(evt.getPropertyName()))
        {
            int progress = (Integer) evt.getNewValue();
            progressAll.setValue(progress);
        }
    }

     class CopyTask extends SwingWorker<Void, Integer>
    {
        private File[] source;
        private File target;
        private long totalBytes = 0L;
        private long copiedBytes = 0L;

        public CopyTask(File[] source, File target)
        {
            this.source = source;
            this.target = target;

            progressAll.setValue(0);
            progressCurrent.setValue(0);
        }

        @Override
        public Void doInBackground() throws Exception
        {	
        	for (File files : source)
        	{
        		retrieveTotalBytes(files);
        	}
        	for (File files : source)
        	{
        		copyFiles(files, new File(target.getAbsolutePath()+ "\\" + files.getName()));
        	}
        	dispose();
        	return null;
        }

        @Override
        public void process(List<Integer> chunks)
        {
            for(int i : chunks)
            {
                progressCurrent.setValue(i);
            }
        }

        @Override
        public void done()
        {
            setProgress(100);
            FileManager.refresh();
        }

        private void retrieveTotalBytes(File sourceFile)
        {
        	if (sourceFile.isDirectory())
        	{
        		File[] files = sourceFile.listFiles();
        		for(File file : files)
        		{
                if(file.isDirectory()) retrieveTotalBytes(file);
                else totalBytes += file.length();
            	}
        	}else totalBytes += sourceFile.length();
        }

        private void copyFiles(File sourceFile, File targetFile) throws IOException
        {
        	    if(sourceFile.isDirectory())
            {
                if(!targetFile.exists()) targetFile.mkdirs();

                String[] filePaths = sourceFile.list();

                for(String filePath : filePaths)
                {
                    File srcFile = new File(sourceFile, filePath);
                    File destFile = new File(targetFile, filePath);
                   
                    copyFiles(srcFile, destFile);
                }
            }
            else
            {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));

                long fileBytes = sourceFile.length();
                long soFar = 0L;

                int theByte;

                while((theByte = bis.read()) != -1)
                {
                    bos.write(theByte);

                    setProgress((int) (copiedBytes++ * 100 / totalBytes));
                    publish((int) (soFar++ * 100 / fileBytes));
                }

                bis.close();
                bos.close();
                publish(100);

            }
        }
    }
}