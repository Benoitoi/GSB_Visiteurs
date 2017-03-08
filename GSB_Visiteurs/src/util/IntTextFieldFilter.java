/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Benoit
 */
public class IntTextFieldFilter extends DocumentFilter
{
    private final int limit;

    /**
     *
     * @param limit
     */
    public IntTextFieldFilter(int limit)
    {
        super();
        this.limit = limit;
    }

    /**
     *
     * @param fb
     * @param offset
     * @param string
     * @param attr
     * @throws BadLocationException
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (test(sb.toString()) && doc.getLength() < limit)
        {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean test(String text)
    {
        try
        {
            Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     *
     * @param fb
     * @param offset
     * @param length
     * @param text
     * @param attrs
     * @throws BadLocationException
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
    {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString()) && doc.getLength() < limit)
        {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    /**
     *
     * @param fb
     * @param offset
     * @param length
     * @throws BadLocationException
     */
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException
    {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (test(sb.toString()) || doc.getLength() - 1 == 0 || sb.toString().equals(""))
        {
            super.remove(fb, offset, length);
        }
    }
}
