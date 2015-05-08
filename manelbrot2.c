//mandelbrot.c -- maps the mandelbrot set.

#include <stdio.h>
#include <math.h>
#include <graphics.h>

const int maxcol = 639;
const int maxrow = 479;
const int max_colors = 8;

int max_iterations = 512;
int max_size = 4;

int main(void) {
	float Pmax = 1.75, Pmin = -1.75, Qmax = 1.5, Qmin = -1.5, P, Q, deltaP, deltaQ, X, Y, Xsquare, Ysquare;
	int color, row, col;	//col is column, not color.
	
	int gd = DETECT, gm;
	initgraph(&gd, &gm, NULL);
	
	deltaP = (Pmax - Pmin)/(maxcol - 1);
	deltaQ = (Qmax - Qmin)/(maxrow - 1);
		
	for (col = 0; col <= maxcol; col++) {
		P = Pmin + col*deltaP;
		for (row = 0; row <= maxrow; row++) {
			Q = Qmin + row*deltaQ;
			X = Y = 0.0;
			color = 0;
			for (color = 0; color < max_iterations; color++) {
				Xsquare = X*X;
				Ysquare = Y*Y;
				if ((Xsquare + Ysquare) > max_size) break;
				
				Y = 2*X*Y + Q;
				X = Xsquare - Ysquare + P;
			} //colors
			putpixel(col, row, (color % max_colors));
		} //rows
	} //columns
	getch();
	closegraph();
	return 0;
}

