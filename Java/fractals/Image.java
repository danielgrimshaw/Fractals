/*
 * Image.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * Generates images based on the Mandelbrot set
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
import fractals.Common;

public class Image extends JComponent {
	private static int LINEWIDTH, OPERATOR, XCENTER, YCENTER, ANGLE;
	private static long PATTERN;

	private int adaptor, mode;
	private int j, k, xscale, yscale, xoffset, yoffset, pr;
	private int [] p, pk;
	private long i;
	private double [] a, b, c, d, e, f;
	private double x, y, newx;
	
	private int cols, rows;
	
	private boolean debug;
    
    public Image(int rows, int cols, boolean debug) {
        this.rows = rows;
        this.cols = cols;
        this.debug = debug;
    }
	synchronized public void paint(Graphics g) {
	    if (debug)
	        System.out.println("Displaying 1");
        a[0] = 0; a[1] = .20; a[2] = -.15; a[3] = .85;
        b[0] = 0; b[1] = -.26; b[2] = .28; b[3] = .04;
        c[0] = 0; c[1] = .23; c[2] = .26; c[3] = -.04;
        d[0] = .16; d[1] = .22; d[2] = .24; d[3] = .85;
        e[0] = 0; e[1] = 0; e[2] = 0; e[3] = 0;
        f[0] = 0; f[1] = 1.60; f[2] = .44; f[3] = 1.6;
        p[0] = 328; p[1] = 2621; p[2] = 4915; p[3] = 32767;
        xscale = 25;
        yscale = 25;
        xoffset = 300;
        yoffset = -50;
        Common.setBackground(g, cols, rows, Color.BLUE);
        image_draw(g, 10, 20000);
        Common.pause();
    }
		
	synchronized private void image_draw(Graphics g, int color, int iterations) {
		int px, py;
		
		x = 0;
		y = 0;
		for (i = 1; i <= iterations; i++) {
			j = (int)(Math.random() * 32768);
			k = (j < p[0]) ? 0 : ((j < p[1]) ? 1 : ((j < p[2]) ? 2 : 3));
			newx = (a[k] * x + b[k] * y + e[k]);
			y = (c[k]*x + d[k]*y + f[k]);
			x = newx;
			px = (int)(x*xscale + xoffset);
			py = (int)(480 - y*yscale + yoffset);
			if ((px >= 0) && (px < 640) && (py >= 0) && (py < 480))
				Common.putPixel(g, px, py, color);
		}
	}
	
	public static void main(String [] args) {
		int frameWidth = 640;
		int frameHeight = 480;
		
		JFrame frame = new JFrame("Fractal Imagery");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setSize(frameWidth, frameHeight/*/frame.getWidth(), frame.getHeight()*/);
		//frame.pack();
		frame.setVisible(true);
		frame.getContentPane().add(new Image(frame.getContentPane().getHeight(), frame.getContentPane().getWidth(), true));
	}
}

