/*
 * Julia.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
package fractals;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import fractals.Fractal;
import fractals.ColorSet;
import fractals.Complex;

public class Julia extends JComponent implements Fractal {
	private static final int MAX_ITERATIONS = 1000;
	
	private final boolean debug;
	private final int maxrow, maxcol;
	
	private Complex c;
	private ColorSet color;
	
	public Julia(int rows, int cols) {
		this(rows, cols, -0.4, 0.6, ColorSet.pinkToGreen, false);
	}
	
	public Julia(int rows, int cols, boolean debug) {
		this(rows, cols, -0.4, 0.6, ColorSet.pinkToGreen, debug);
	}
	
	public Julia(int rows, int cols, double ca, double cb) {
		this(rows, cols, ca, cb, ColorSet.pinkToGreen, false);
	}
	
	public Julia(int rows, int cols, double ca, double cb, ColorSet colors, boolean debug) {
		this.debug = debug;
		this.maxrow = rows;
		this.maxcol = cols;
		this.c = new Complex(ca, cb);
		this.color = colors;
	}
	
	synchronized public void paint(Graphics g) {
		if (debug)
			System.out.println("Painting...");
		
		int row, col; //col is column, not color
		
		double pMax = 1.5, pMin = -1.5, qMax = 1.5, qMin = -1.5;
		double deltaP = (pMax - pMin)/maxcol;
		double deltaQ = (qMax - qMin)/maxrow;
		
		for (col = 0; col < maxcol; col++) {
			for (row = 0; row < maxrow; row++) {
				double p = pMin + col*deltaP; // Scaled x-coordinate of the point
				double q = qMin + row*deltaQ; // Scaled y-coordinate of the point
				Complex z = new Complex(p, q);
				double smoothcolor = Math.exp(-z.abs());
				
				for (int i = 0; i < MAX_ITERATIONS && z.abs() < 30; i++) {
					z = f(z);
					smoothcolor += Math.exp(-z.abs());
				}
				
				smoothcolor = smoothcolor/MAX_ITERATIONS;
				putPixel(g, col, row, 
						Color.HSBtoRGB(color.getHue() + (float)(color.getFactor()*smoothcolor),
										color.getSaturation(), 
										color.getBrightness()));
			}
		}
	}
	
	public Complex f(Complex z) {
		// f(z) = z^2 + C
		return z.times(z).plus(c);
	}
	
	public void putPixel(Graphics g, int col, int row, int color) {
		if (col > maxcol)
			throw new IllegalArgumentException("Column value is too large");
		if (row > maxrow)
			throw new IllegalArgumentException("Row value is too large");
		g.setColor(new Color(color));
		g.drawLine(col,row,col,row);
	}
	
	public static void main (String args[]) {
		int frameWidth = 640;
		int frameHeight = 480;
		
		JFrame frame = new JFrame("Julia Set");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setSize(frameWidth, frameHeight/*/frame.getWidth(), frame.getHeight()*/);
		//frame.pack();
		frame.setVisible(true);
		frame.add(new Julia(frame.getContentPane().getHeight(), frame.getContentPane().getWidth(), -0.4, 0.6, ColorSet.pinkToGreen, true));
	}
}

