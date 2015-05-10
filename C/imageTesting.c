//imageTesting.c --this is where you test the array to see what images it produces.
#include <stdio.h>
#include <math.h>
#include <graphics.h>
#include <stdlib.h>

void image_draw(int color, int iterations);
void wait();

//GLOBALS
int LINEWIDTH, OPERATOR, XCENTER, YCENTER, ANGLE;
unsigned long int PATTERN;

int adaptor, mode;
int j, k, xscale, yscale, xoffset, yoffset, pr, p[4], pk[4];
long unsigned int i;
float a[4], b[4], c[4], d[4], e[4], f[4], x, y, newx;

int main(void) {
	int gd = DETECT, gm;
	srand((unsigned int)rand() % 65536);
	initgraph(&gd, &gm, NULL);
	a[0] = 1; a[1] = 1; a[2] = 1; a[3] = 1;
	b[0] = 1; b[1] = 1; b[2] = 1; b[3] = 1;
	c[0] = 1; c[1] = 1; c[2] = 1; c[3] = 1;
	d[0] = 1; d[1] = 1; d[2] = 1; d[3] = 1;
	e[0] = 0; e[1] = 0; e[2] = 0; e[3] = 0;
	f[0] = 1; f[1] = 1; f[2] = 1; f[3] = 1;
	p[0] = 32767; p[1] = 32767; p[2] = 32767; p[3] = 32767;
	xscale = 1;
	yscale = 1;
	xoffset = 0;
	yoffset = 0;
	cleardevice();
	setbkcolor(1);
	image_draw(10, 20000);
	wait();
	return 0;
}

void image_draw(int color, int iterations) {
	int px, py;
	
	x = 0;
	y = 0;
	for (i = 1; i <= iterations; i++) {
		j = (rand() % 32767);
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

void wait() {
	getch();
	cleardevice();
	setbkcolor(0);
}
