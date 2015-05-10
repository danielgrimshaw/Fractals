//image.c -- penerates images through usage of fractals and iterated function systems
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
	cleardevice();
	setbkcolor(1);
	image_draw(10, 20000);
	wait();
	
	a[0] = 0; a[1] = .1; a[2] = .42; a[3] = .42;
	b[0] = 0; b[1] = 0; b[2] = -.42; b[3] = .42;
	c[0] = 0; c[1] = 0; c[2] = .42; c[3] = -.42;
	d[0] = .5; d[1] = .1; d[2] = .42; d[3] = .42;
	e[0] = 0; e[1] = 0; e[2] = 0; e[3] = 0;
	f[0] = 0; f[1] = .2; f[2] = .2; f[3] = .2;
	p[0] = 1638; p[1] = 6553; p[2] = 19660; p[3] = 32767;
	xscale = 750;
	yscale = 750;
	xoffset = 300;
	yoffset = 00;
	cleardevice();
	setbkcolor(1);
	image_draw(13, 20000);
	wait();
	
	a[0] = .5; a[1] = .5; a[2] = .5; a[3] = 0;
	b[0] = 0; b[1] = 0; b[2] = 0; b[3] = 0;
	c[0] = 0; c[1] = 0; c[2] = 0; c[3] = 0;
	d[0] = .5; d[1] = .5; d[2] = .5; d[3] = 0;
	e[0] = 0; e[1] = 1.; e[2] = .5; e[3] = 0;
	f[0] = 0; f[1] = 0; f[2] = .5; f[3] = 0;
	p[0] = 10813; p[1] = 21626; p[2] = 32767; p[3] = 32767;
	xscale = 180;
	yscale = 180;
	xoffset = 150;
	yoffset = -50;
	cleardevice();
	setbkcolor(7);
	image_draw(5, 20000);
	wait();
	
	cleardevice();
	setbkcolor(7);
	a[0] = .333; a[1] = .333; a[2] = .667; a[3] = 0;
	b[0] = 0; b[1] = 0; b[2] = 0; b[3] = 0;
	c[0] = 0; c[1] = 0; c[2] = 0; c[3] = 0;
	d[0] = .333; d[1] = .333; d[2] = .667; d[3] = 0;
	e[0] = 0; e[1] = 1.; e[2] = .5; e[3] = 0;
	f[0] = 0; f[1] = 0; f[2] = .5; f[3] = 0;
	p[0] = 10813; p[1] = 21626; p[2] = 32767; p[3] = 32767;
	xscale = 180;
	yscale = 180;
	xoffset = 150;
	yoffset = -50;
	image_draw(4, 50000);
	wait();
	
	cleardevice();
	setbkcolor(7);
	a[0] = .5; a[1] = .5; a[2] = .5; a[3] = .5;
	b[0] = 0; b[1] = 0; b[2] = 0; b[3] = 0;
	c[0] = 0; c[1] = 0; c[2] = 0; c[3] = 0;
	d[0] = .5; d[1] = .5; d[2] = .5; d[3] = .5;
	e[0] = 0; e[1] = .5; e[2] = 0; e[3] = .5;
	f[0] = 0; f[1] = 0; f[2] = .5; f[3] = .5;
	p[0] = 8192; p[1] = 16383; p[2] = 24575; p[3] = 32767;
	xscale = 250;
	yscale = 200;
	xoffset = 200;
	yoffset = -50;
	image_draw(1, 100000);
	wait();
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