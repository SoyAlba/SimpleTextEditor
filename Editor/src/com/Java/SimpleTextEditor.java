package Java;
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
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import utilities.*;

public class SimpleTextEditor extends JFrame implements ActionListener, TextEditorFunction {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, formatMenu;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem, cutMenuItem, copyMenuItem,
            pasteMenuItem, selectAllMenuItem, fontMenuItem,fontSizeMenuItem, colorMenuItem,colorBackgroundMenuItem;
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

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('a');

        newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic('n');
        newMenuItem.addActionListener(this);

        openMenuItem = new JMenuItem("Open");
        openMenuItem.setMnemonic('a');
        openMenuItem.addActionListener(this);

        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic('g');

        saveMenuItem.addActionListener(this);

        saveAsMenuItem = new JMenuItem("Save as...");
        saveAsMenuItem.setMnemonic('u');
        saveAsMenuItem.addActionListener(this);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('s');
        exitMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic('e');

        cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setMnemonic('t');
        cutMenuItem.addActionListener(this);

        copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setMnemonic('c');
        copyMenuItem.addActionListener(this);

        pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.addActionListener(this);

        selectAllMenuItem = new JMenuItem("Select All");
        selectAllMenuItem.setMnemonic('a');
        selectAllMenuItem.addActionListener(this);

        fontMenuItem = new JMenuItem("Font");
        fontMenuItem.setMnemonic('f');
        fontMenuItem.addActionListener(this);

        fontSizeMenuItem = new JMenuItem("Font Size");
        fontSizeMenuItem.setMnemonic('z');
        fontSizeMenuItem.addActionListener(this);

        colorMenuItem = new JMenuItem("Color");
        colorMenuItem.setMnemonic('c');
        colorMenuItem.addActionListener(this);


        // Add the menu bar to the window
        setJMenuBar(menuBar);
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // Create a new ImageIcon with a smaller size
        ImageIcon newIcon = new ImageIcon(getClass().getResource("/icons/new.png"));
        newIcon.setImage(newIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        newButton = new JButton(newIcon);
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        
        // Repeat the same process for the other icons
        ImageIcon openIcon = new ImageIcon(getClass().getResource("/icons/open.png"));
        openIcon.setImage(openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        openButton = new JButton(openIcon);
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/icons/save.png"));
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        
        ImageIcon cutIcon = new ImageIcon(getClass().getResource("/icons/cut.png"));
        cutIcon.setImage(cutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        cutButton = new JButton(cutIcon);
        cutButton.setToolTipText("Cut");
        cutButton.addActionListener(this);
        
        ImageIcon copyIcon = new ImageIcon(getClass().getResource("/icons/copy.png"));
        copyIcon.setImage(copyIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        copyButton = new JButton(copyIcon);
        copyButton.setToolTipText("Copy");
        copyButton.addActionListener(this);
        
        ImageIcon pasteIcon = new ImageIcon(getClass().getResource("/icons/paste.png"));
        pasteIcon.setImage(pasteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        pasteButton = new JButton(pasteIcon);
        pasteButton.setToolTipText("Paste");
        pasteButton.addActionListener(this);

        ImageIcon fontIcon = new ImageIcon(getClass().getResource("/icons/font.png"));
        fontIcon.setImage(fontIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        fontMenuItem = new JMenuItem("Font", fontIcon);
        fontMenuItem.setMnemonic('f');
        fontMenuItem.addActionListener(this);

        ImageIcon fontSizeIcon = new ImageIcon(getClass().getResource("/icons/size.png"));
        fontSizeIcon.setImage(fontSizeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        fontSizeMenuItem = new JMenuItem("Font Size", fontSizeIcon);
        fontSizeMenuItem.setMnemonic('z');
        fontSizeMenuItem.addActionListener(this);

        ImageIcon colorIcon = new ImageIcon(getClass().getResource("/icons/color.png"));
        colorIcon.setImage(colorIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        colorMenuItem = new JMenuItem("Color", colorIcon);
        colorMenuItem.setMnemonic('c');
        colorMenuItem.addActionListener(this);

        // Add the buttons to the tool bar
        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);
        toolBar.addSeparator();
        toolBar.add(fontMenuItem);
        toolBar.add(fontSizeMenuItem);
        toolBar.add(colorMenuItem);

        // Add the tool bar to the window
        add(toolBar, BorderLayout.NORTH);

        // Create a status bar
        statusBar = new JPanel();
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.setPreferredSize(new java.awt.Dimension(getWidth(), 20));
        statusBar.setLayout(new BorderLayout());

        statusLabel = new JLabel("Ready");
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
        textArea.setMargin(new Insets(10, 10, 10, 10));
        
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
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("HTML Files", "html"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Word Files", "doc"));
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
    statusLabel = new JLabel(" Ready ");
    statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
    statusBar.add(statusLabel);

    // Add the scroll pane to the window
    add(scrollPane, BorderLayout.CENTER);

    // Add the status bar to the window
    add(statusBar, BorderLayout.SOUTH);

    // Create the format menu and menu items
    formatMenu = new JMenu("Format");
    formatMenu.setMnemonic('f');

    fontMenuItem = new JMenuItem("font...");
    fontMenuItem.setMnemonic('u');
    fontMenuItem.addActionListener(this);

    fontSizeMenuItem = new JMenuItem("Font Size...");
    fontSizeMenuItem.setMnemonic('z');
    fontSizeMenuItem.addActionListener(this);

    colorMenuItem = new JMenuItem("Color...");
    colorMenuItem.setMnemonic('c');
    colorMenuItem.addActionListener(this);

    formatMenu.add(fontMenuItem);
    formatMenu.add(colorMenuItem);
    formatMenu.add(fontSizeMenuItem);

    // Add the file, edit, and format menus to the menu bar
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(formatMenu);
        // Add a label to the status bar
        statusLabel = new JLabel(" Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);
    
        // Add the status bar to the window
        add(statusBar, BorderLayout.SOUTH);
    
        // Add the scroll pane to the window
        add(scrollPane, BorderLayout.CENTER);
    
        // Create the format menu
        formatMenu = new JMenu("Format");
        formatMenu.setMnemonic('f');
    
        fontMenuItem = new JMenuItem("Font");
        fontMenuItem.setMnemonic('u');
        fontMenuItem.addActionListener(this);
    
        colorMenuItem = new JMenuItem("Color text");
        colorMenuItem.setMnemonic('c');
        colorMenuItem.addActionListener(this);
    
        formatMenu.add(fontMenuItem);
        formatMenu.add(colorMenuItem);
        formatMenu.add(fontSizeMenuItem);
    
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
            selectLetterColor();
        }else if (e.getSource() == fontMenuItem) {
            selectFont();
        }else if (e.getSource() == fontSizeMenuItem) {
            selectFontSize();
        } else if (e.getSource() == colorBackgroundMenuItem) {
            selectLeterBackgroundColor();
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
                statusLabel.setText("File saved");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void exitApplication(){
        if (textArea.getText().length() > 0) {
            int response = showConfirmDialog("You want to save the changes to the current file?");
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
        statusLabel.setText("Text cut");
    }
    private void copy() {
        textArea.copy();
        statusLabel.setText("Text copied");
    }
    private void paste() {
        textArea.paste();
        statusLabel.setText("Text pasted");
    }

    private void selectAll() {
        textArea.selectAll();
        statusLabel.setText("select all");
    }
    private void selectLetterColor() {
        Color color = JColorChooser.showDialog(this, "Select color", Color.BLACK);
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            JTextPane textPane = new JTextPane();
            textPane.setFont(textArea.getFont());
            textPane.setForeground(textArea.getForeground());
            textPane.setText(selectedText);
            StyledDocument doc = textPane.getStyledDocument();
            Style style = doc.addStyle("highlight", null);
            StyleConstants.setForeground(style, color);
            doc.setCharacterAttributes(0, selectedText.length(), style, false);
            textArea.replaceSelection(textPane.getText());
            statusLabel.setText("color selected");
        }
    }
    
    
    private List<Object> highlights = new ArrayList<>();
    private void selectLeterBackgroundColor() {
        Color color = JColorChooser.showDialog(this, "Select color", Color.BLACK);
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        Highlighter highlighter = textArea.getHighlighter();
        Object highlight = null;
        try {
            highlight = highlighter.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(color));
            highlights.add(highlight);
            statusLabel.setText("color selected");
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    private void selectFont() {
        Font font = JFontChooser.showDialog(this);
        textArea.setFont(font);
        statusLabel.setText("Font selected");
    }
    private void selectFontSize() {
        String size = JOptionPane.showInputDialog(this, "enter font size", "font size", JOptionPane.PLAIN_MESSAGE);
        if (size != null) {
            try {
                int fontSize = Integer.parseInt(size);
                Font font = textArea.getFont();
                font = font.deriveFont((float) fontSize);
                textArea.setFont(font);
                statusLabel.setText("Font size selected");
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "invalid size", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}

