package org.vikor.Methods;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class Example extends JFrame {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -7472756789216456359L;

	public static void createGUI() {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        String[] columnNames = { "Name", "Modified", "Type", "Size" };
 
        String[][] data = { { "addins", "02.11.2006 19:15", "Folder", "" },
                { "AppPatch", "03.10.2006 14:10", "Folder", "" },
                { "assembly", "02.11.2006 14:20", "Folder", "" },
                { "Boot", "13.10.2007 10:46", "Folder", "" },
                { "Branding", "13.10.2007 12:10", "Folder", "" },
                { "Cursors", "23.09.2006 16:34", "Folder", "" },
                { "Debug", "07.12.2006 17:45", "Folder", "" },
                { "Fonts", "03.10.2006 14:08", "Folder", "" },
                { "Help", "08.11.2006 18:23", "Folder", "" },
                { "explorer.exe", "18.10.2006 14:13", "File", "2,93MB" },
                { "helppane.exe", "22.08.2006 11:39", "File", "4,58MB" },
                { "twunk.exe", "19.08.2007 10:37", "File", "1,08MB" },
                { "nsreg.exe", "07.08.2007 11:14", "File", "2,10MB" },
                { "avisp.exe", "17.12.2007 16:58", "File", "12,67MB" }, };
 
        final JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
 
        JButton button = new JButton("Save to xml");
 
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DocumentBuilderFactory f = DocumentBuilderFactory
                            .newInstance();
                    DocumentBuilder builder = f.newDocumentBuilder();
                    File file = new File("table.xml");
 
                    if (!file.exists()) {
                        file.createNewFile();
                    }
 
                    Document doc = builder.newDocument();
                    Element tableEl = doc.createElement("table");
                    doc.appendChild(tableEl);
 
                    TableModel model = table.getModel();
                    TableColumnModel columns = table.getColumnModel();
 
                    for (int i = 0; i < model.getRowCount(); i++) {
                        Element rowEl = doc.createElement("row");
                        tableEl.appendChild(rowEl);
 
                        for (int j = 0; j < columns.getColumnCount(); j++) {
                            TableColumn col = columns.getColumn(j);
                            String header = col.getHeaderValue().toString();
                            String value = model.getValueAt(i, j).toString();
                            Element cellEl = doc.createElement("cell");
                            Attr colAttr = doc.createAttribute("colName");
                            cellEl.setAttributeNode(colAttr);
                            rowEl.appendChild(cellEl);
                            colAttr.appendChild(doc.createTextNode(header));
                            cellEl.appendChild(doc.createTextNode(value));
                        }
                    }
 
                    TransformerFactory tFactory = TransformerFactory
                            .newInstance();
                    Transformer transformer = tFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(file);
                    transformer.transform(source, result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
 
        frame.getContentPane().add(button, BorderLayout.SOUTH);
 
        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }
}