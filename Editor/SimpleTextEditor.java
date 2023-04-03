import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SimpleTextEditor extends JFrame implements ActionListener, TextEditorFunction {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, formatMenu;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem, cutMenuItem, copyMenuItem,
            pasteMenuItem, selectAllMenuItem, fontMenuItem, colorMenuItem;
    private JToolBar toolBar;
    private JButton newButton, openButton, saveButton, cutButton, copyButton, pasteButton;
    private JPanel statusBar;
    private JLabel statusLabel;

    public SimpleTextEditor() {
        super("Simple Text Editor");

        // Set the default size and position of the window
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Create a text area and add it to a scroll pane
        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        scrollPane = new JScrollPane(textArea);
        

        // Create the menu bar and menu items
        menuBar = new JMenuBar();

        fileMenu = new JMenu("Archivo");
        fileMenu.setMnemonic('a');

        newMenuItem = new JMenuItem("Nuevo");
        newMenuItem.setMnemonic('n');
        newMenuItem.addActionListener(this);

        openMenuItem = new JMenuItem("Abrir");
        openMenuItem.setMnemonic('a');
        openMenuItem.addActionListener(this);

        saveMenuItem = new JMenuItem("Guardar");
        saveMenuItem.setMnemonic('g');
        saveMenuItem.addActionListener(this);

        saveAsMenuItem = new JMenuItem("Guardar como...");
        saveAsMenuItem.setMnemonic('u');
        saveAsMenuItem.addActionListener(this);

        exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.setMnemonic('s');
        exitMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu = new JMenu("Editar");
        editMenu.setMnemonic('e');

        cutMenuItem = new JMenuItem("Cortar");
        cutMenuItem.setMnemonic('t');
        cutMenuItem.addActionListener(this);

        copyMenuItem = new JMenuItem("Copiar");
        copyMenuItem.setMnemonic('c');
        copyMenuItem.addActionListener(this);

        pasteMenuItem = new JMenuItem("Pegar");
        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.addActionListener(this);

        selectAllMenuItem = new JMenuItem("Seleccionar todo");
        selectAllMenuItem.setMnemonic('a');
        selectAllMenuItem.addActionListener(this);

        // Add the menu bar to the window
        setJMenuBar(menuBar);
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // Create a new ImageIcon with a smaller size
        ImageIcon newIcon = new ImageIcon(getClass().getResource("icons/new.png"));
        newIcon.setImage(newIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        newButton = new JButton(newIcon);
        newButton.setToolTipText("Nuevo");
        newButton.addActionListener(this);
        
        // Repeat the same process for the other icons
        ImageIcon openIcon = new ImageIcon(getClass().getResource("icons/open.png"));
        openIcon.setImage(openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        openButton = new JButton(openIcon);
        openButton.setToolTipText("Abrir");
        openButton.addActionListener(this);
        
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("icons/save.png"));
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Guardar");
        saveButton.addActionListener(this);
        
        ImageIcon cutIcon = new ImageIcon(getClass().getResource("icons/cut.png"));
        cutIcon.setImage(cutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        cutButton = new JButton(cutIcon);
        cutButton.setToolTipText("Cortar");
        cutButton.addActionListener(this);
        
        ImageIcon copyIcon = new ImageIcon(getClass().getResource("icons/copy.png"));
        copyIcon.setImage(copyIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        copyButton = new JButton(copyIcon);
        copyButton.setToolTipText("Copiar");
        copyButton.addActionListener(this);
        
        ImageIcon pasteIcon = new ImageIcon(getClass().getResource("icons/paste.png"));
        pasteIcon.setImage(pasteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        pasteButton = new JButton(pasteIcon);
        pasteButton.setToolTipText("Pegar");
        pasteButton.addActionListener(this);
        
        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);
        toolBar.addSeparator();
        

        // Add the tool bar to the window
        add(toolBar, BorderLayout.NORTH);

        // Create a status bar
        statusBar = new JPanel();
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.setPreferredSize(new java.awt.Dimension(getWidth(), 20));
        statusBar.setLayout(new BorderLayout());

        statusLabel = new JLabel("Listo");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel, BorderLayout.WEST);

        // Add the status bar to the window
        add(statusBar, BorderLayout.SOUTH);

        // Display the window
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add the scroll pane to the window
        add(scrollPane, BorderLayout.CENTER);

        // Add the menu items to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Add the menu items to the edit menu
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.addSeparator();
        editMenu.add(selectAllMenuItem);

        // Add the text area to the scroll panel
        scrollPane.setViewportView(textArea);

        // Add the menu bar to the window
        setJMenuBar(menuBar);

        // Add the scroll pane to the window
        add(scrollPane, BorderLayout.CENTER);

        // set the space for the text area
        textArea.setMargin(new Insets(5, 5, 5, 5));
        

    }

    public static void main(String[] args) {
        // Set the look and feel of the window to match the operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Create a new instance of the SimpleTextEditor class
        new SimpleTextEditor();
    }

@Override
public void actionPerformed2(ActionEvent e) {
    // Check if the user clicked the new menu item
    if (e.getSource() == newMenuItem) {
        // Clear the text area
        textArea.setText("");
        // Set the title of the window to the default title
        setTitle("Simple Text Editor");
        // Set the status label to "Ready"
        statusLabel.setText("Listo");
    }

    // Check if the user clicked the open menu item
    if (e.getSource() == openMenuItem) {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        // Set the file filter to only show text files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if the user clicked the open button
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File file = fileChooser.getSelectedFile();

            try {
                // Create a file reader
                FileReader fileReader = new FileReader(file);
                try (// Create a buffered reader
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    // Read the first line of the file
                    String line = bufferedReader.readLine();
                    // Clear the text area
                    textArea.setText("");

                    // Loop through the lines of the file
                    while (line != null) {
                        // Append the line to the text area
                        textArea.append(line + ""); 
                        // Read the next line
                        line = bufferedReader.readLine();
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
    statusLabel = new JLabel(" Listo");
    statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
    statusBar.add(statusLabel);

    // Add the scroll pane to the window
    add(scrollPane, BorderLayout.CENTER);

    // Add the status bar to the window
    add(statusBar, BorderLayout.SOUTH);

    // Create the format menu and menu items
    formatMenu = new JMenu("Formato");
    formatMenu.setMnemonic('f');

    fontMenuItem = new JMenuItem("Fuente...");
    fontMenuItem.setMnemonic('u');
    fontMenuItem.addActionListener(this);

    colorMenuItem = new JMenuItem("Color...");
    colorMenuItem.setMnemonic('c');
    colorMenuItem.addActionListener(this);

    formatMenu.add(fontMenuItem);
    formatMenu.add(colorMenuItem);

    // Add the file, edit, and format menus to the menu bar
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(formatMenu);
        // Add a label to the status bar
        statusLabel = new JLabel("Listo");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);
    
        // Add the status bar to the window
        add(statusBar, BorderLayout.SOUTH);
    
        // Add the scroll pane to the window
        add(scrollPane, BorderLayout.CENTER);
    
        // Create the format menu
        formatMenu = new JMenu("Formato");
        formatMenu.setMnemonic('f');
    
        fontMenuItem = new JMenuItem("Fuente");
        fontMenuItem.setMnemonic('u');
        fontMenuItem.addActionListener(this);
    
        colorMenuItem = new JMenuItem("Color de fondo");
        colorMenuItem.setMnemonic('c');
        colorMenuItem.addActionListener(this);
    
        formatMenu.add(fontMenuItem);
        formatMenu.add(colorMenuItem);
    
        // Add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
    
        // Enable/disable appropriate menu items and buttons
        updateMenuItemAndButtonStatus(false, false, false, false, true, false, false, false, false, false, false, false);
    
        // Display the window
        setVisible(true);
    
    }
    
    private void updateMenuItemAndButtonStatus(boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean h,
        boolean i, boolean j, boolean k, boolean l, boolean m) {
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newMenuItem || e.getSource() == newButton) {
            newFile();
        } else if (e.getSource() == openMenuItem || e.getSource() == openButton) {
            openFile();
        } else if (e.getSource() == saveMenuItem || e.getSource() == saveButton) {
            saveFile();
        } else if (e.getSource() == saveAsMenuItem) {
            saveFileAs();
        } else if (e.getSource() == exitMenuItem) {
            exitApplication();
        } else if (e.getSource() == cutMenuItem || e.getSource() == cutButton) {
            cut();
        } else if (e.getSource() == copyMenuItem || e.getSource() == copyButton) {
            copy();
        } else if (e.getSource() == pasteMenuItem || e.getSource() == pasteButton) {
            paste();
        } else if (e.getSource() == selectAllMenuItem) {
            selectAll();
        } else if (e.getSource() == colorMenuItem) {
            selectBackgroundColor();
        }
    }
    // create a new file
    private void newFile() {
        if (textArea.getText().length() > 0) {
            int response = showConfirmDialog("¿Desea guardar los cambios en el archivo actual?");
            if (response == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (response == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        textArea.setText("");
        setTitle("Nuevo archivo");
        updateMenuItemAndButtonStatus(false, false, false, false, true, false, false, false, false, false, false, false);
        statusLabel.setText("Archivo nuevo creado");
    }
    // open a file
    private void openFile(){
        if (textArea.getText().length() > 0) {
            int response = showConfirmDialog("¿Desea guardar los cambios en el archivo actual?");
            if (response == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (response == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileReader fileReader = new FileReader(file);
                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String line = bufferedReader.readLine();
                    textArea.setText("");
                    while (line != null) {
                        textArea.append(line + ""); 
                        line = bufferedReader.readLine();
                    }
                }
                setTitle(file.getName());
                updateMenuItemAndButtonStatus(true, true, true, true, true, true, true, true, true, true, true, true);
                statusLabel.setText("Archivo abierto");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private int showConfirmDialog(String string) {
        return 0;
    }
    // save a file
    private void saveFile() {
        if (getTitle().equals("Nuevo archivo")) {
            saveFileAs();
        } else {
            try {
                FileWriter fileWriter = new FileWriter(getTitle());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(textArea.getText());
                bufferedWriter.close();
                statusLabel.setText("Archivo guardado");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(textArea.getText());
                bufferedWriter.close();
                setTitle(file.getName());
                updateMenuItemAndButtonStatus(true, true, true, true, true, true, true, true, true, true, true, true);
                statusLabel.setText("Archivo guardado");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void exitApplication(){
        if (textArea.getText().length() > 0) {
            int response = showConfirmDialog("¿Desea guardar los cambios en el archivo actual?");
            if (response == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (response == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        System.exit(0);

    }
    private void cut() {
        textArea.cut();
        statusLabel.setText("Texto cortado");
    }
    private void copy() {
        textArea.copy();
        statusLabel.setText("Texto copiado");
    }
    private void paste() {
        textArea.paste();
        statusLabel.setText("Texto pegado");
    }

    private void selectAll() {
        textArea.selectAll();
        statusLabel.setText("Seleccionar todo");
    }
    private void selectBackgroundColor() {
        Color color = JColorChooser.showDialog(this, "Seleccionar color de fondo", textArea.getBackground());
        if (color != null) {
            textArea.setBackground(color);
        }
    }
    
}

