/*
UNIVERSIDAD DE LAS FUERZAS ARMADAS - ESPE
            CARRERA SOFTWARE
NOMBRE: RUBÉN ARROYO                         PERÍODO: 202551
ASIGNATURA: FUNDAMENTOS DE PROGRAMACIÓN      NRC:
FECHA: 14/11/2025

Tema: ejercio que permite ingresar los datos del Atleta y calcular su distancia

*/
//Libreria
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <math.h>

//Declaración de var glob, CTES, funciones
double velocidad=0;
double angulo=0, anguloRadianes=0;
#define PI 3.1416
#define g 9.8       //g representa la gravedad

//Ingreso de datos
void ingresarDatos(){
    printf ("\nC%clculo de distancia",160);
    printf ("\nIngrese la velocidad: ");
    scanf("%lf", &velocidad);
    printf ("\nIngrese el %cngulo: ",160);
    scanf("%lf", &angulo);
}
double calcularDistancia(double distancia);
void mostrarDatos(double distancia);

//prog princ
int main (){
    //Declar var local
    double distancia=0;

    //Llamadas a la funcion
    ingresarDatos();
    distancia = calcularDistancia(distancia);
    mostrarDatos(distancia);
    getch();
    return 0;
}

//funcion que permite cálcular Distancia
double calcularDistancia(double distancia){
    anguloRadianes =  angulo * PI /180;
    distancia = pow(velocidad, 2)*sin(2*anguloRadianes)/g;
    return distancia;
}
//mostrar Datos
void mostrarDatos(double distancia){
    printf ("\nLa velocidad ingresada es: %.3lf", velocidad);
    printf ("\nEl angulo ingresado es: %.2lf", angulo);
    printf ("\nLa distancia es: %.2f ", distancia);
}
