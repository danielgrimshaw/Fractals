//mandelbrot.c -- maps the mandelbrot set.

#include <stdio.h>
#include <math.h>
#include <graphics.h>

const int maxcol = 639;
const int maxrow = 479;
const int max_colors = 8;

int max_iterations = 1024;
int max_size = 4;
float Q[480];

int main(void) {
	float Pmax = 1.75, Pmin = -1.75, Qmax = 1.5, Qmin = -1.5, P, deltaP, deltaQ, X, Y, Xsquare, Ysquare;
	int color, row, col;	//col is column, not color.
	
	int gd = DETECT, gm;
	initgraph(&gd, &gm, NULL);
	
	deltaP = (Pmax - Pmin)/(maxcol - 1);
	deltaQ = (Qmax - Qmin)/(maxrow - 1);
	
	for (row = 0; row <= maxrow; row++)
		Q[row] = Qmin + row*deltaQ;
		
	for (col = 0; col <= maxcol; col++) {
		P = Pmin + col*deltaP;
		for (row = 0; row <= maxrow; row++) {
			X = Y = 0.0;
			color = 0;
			for (color = 0; color < max_iterations; color++) {
				Xsquare = X*X;
				Ysquare = Y*Y;
				if ((Xsquare + Ysquare) > max_size) break;
				
				Y = 2*X*Y + Q[row];
				X = Xsquare - Ysquare + P;
			} //colors
			putpixel(col, row, (color % max_colors));
		} //rows
	} //columns
	getch();
	closegraph();
	return 0;
}