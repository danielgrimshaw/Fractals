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
import java.util.Scanner;

public class Image extends JComponent {
	int LINEWIDTH, OPERATOR, XCENTER, YCENTER, ANGLE;
	long PATTERN;

	int adaptor, mode;
	int j, k, xscale, yscale, xoffset, yoffset, pr;
	int [] p, pk;
	long i;
	float [] a, b, c, d, e, f, x, y, newx;
	
   private void wait() {
      Scanner s = new Scanner(System.in);
      s.next();
   }
      
	synchronized private void paint(Graphics g) {
      
	}
		
	synchronized private void image_draw(int color, int iterations) {
		int px, py;
		
		x = 0;
		y = 0;
		for (i = 1; i <= iterations; i++) {
			j = (int)(Math.random() * 32768);
			k = (j < p[0]) ? 0 :((j < p[1]) ? 1 : ((j < p[2]) ? 2 : 3));
			newx = (a[k]*x + b[k]*y + e[k]);
			y = (c[k]*x + d[k]*y + f[k]);
			x = newx;
			px = x*xscale + xoffset;
			py = (480 - y*yscale + yoffset);
			if ((px >= 0) && (px < 640) && (py >= 0) && (py < 480))
				putpixel(px, py, color);
		}
	}
}

