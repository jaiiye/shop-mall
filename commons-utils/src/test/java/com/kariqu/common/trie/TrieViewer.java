package com.kariqu.common.trie;

/**
 * 图形化测试，从文件中读取单词
 * User: Asion
 * Date: 12-6-22
 * Time: 下午3:25
 */

import com.kariqu.common.trie.swing.ViewableTrie;

import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;


public class TrieViewer extends JFrame {

    private ViewableTrie trie;

    public TrieViewer() {
        trie = new ViewableTrie(false);
    }

    public void go() throws Exception {
        JTree jTree = new JTree(trie.getTreeModel());
        getContentPane().add(new JScrollPane(jTree));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Trie Visualizer");
        setPreferredSize(new Dimension(500, 600));
        pack();
        setVisible(true);
    }

    /**
     * Adds the words in file f to the dictionary, where word is
     * a series of characters surrounded by whitespace.
     *
     * @param f A file containing words.
     */
    public void addToDictionary(File f)
            throws IOException {
        long t = System.currentTimeMillis();
        final int bufSize = 1000;
        int read;
        int numWords = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            char[] buf = new char[bufSize];
            while ((read = fr.read(buf)) != -1) {
                // TODO modify this split regex to actually be useful
                String[] words = new String(buf, 0, read).split("\\W");
                for (String s : words) {
                    trie.insert(s);
                    numWords++;
                }
            }
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                }
            }
        }
        System.out.println("Read from file and inserted " + numWords + " words into trie in " +
                (System.currentTimeMillis() - t) / 1000.0 + " seconds.");
    }

    public static void main(String[] args) throws Exception {
        TrieViewer tv = new TrieViewer();
        tv.addToDictionary(new File("D:\\test.txt"));
        tv.go();
    }
}