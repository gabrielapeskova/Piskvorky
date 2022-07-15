/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package cz.itnetwork.piskvorkyhra;

import java.util.Scanner;

/**
 *
 * @author Gábinka
 */
public class PiskvorkyHra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "Windows-1250");
        //odstaneni nulovych hodnot z pole
        String[][] piskvorky = new String[11][11];
        for (int m = 0; m < piskvorky[0].length; m++) {
            for (int n = 0; n < piskvorky.length; n++) {
                piskvorky[m][n] = " ";
            }
        }
        //zadani osy x a y do hraciho pole
        for (int i = 2; i < piskvorky.length; i++) {
            piskvorky[i - 1][0] = Integer.toString(i - 1);
            piskvorky[0][i] = Integer.toString(i - 1);
        }
        //vypsani hraciho pole
        for (int k = 0; k < piskvorky[0].length - 1; k++) {
            for (int l = 0; l < piskvorky.length; l++) {
                System.out.print(piskvorky[k][l]);
            }
            System.out.println();
        }
        String sloupec = "";
        String radek = "";
        String levaDiagonala1 = "";
        String levaDiagonala2 = "";
        String pravaDiagonala1 = "";
        String pravaDiagonala2 = "";
        String remiza = "";
        String vitez = "?";
        String vitezO = "OOOOO";
        String vitezX = "XXXXX";
        int rada = 0;
        do {
            int x;
            int y;
            boolean platnostZadani;
            //zadávání tahů hráči
            do {
                System.out.println();
                if(rada % 2 == 0){
                 System.out.println("Na řadě je hráč s kolečky");   
                }
                else {
                   System.out.println("Na řadě je hráč s křížky"); 
                }
                System.out.println("Zadej pozici X kam chceš táhnout:");
                x = Integer.parseInt(sc.nextLine());
                System.out.println("Zadej pozici Y kam chceš táhnout:");
                y = Integer.parseInt(sc.nextLine());
                if ((((x) <= 10) && (1 <= (x))) && (((y + 1) <= 10) && (1 <= (y + 1))) && (piskvorky[y][x + 1]).equals(" ")) {
                    platnostZadani = true;
                    if(rada % 2 == 0){
                        piskvorky[y][x + 1] = "O";
                    }
                    else {
                       piskvorky[y][x + 1] = "X"; 
                    }
                } else {
                    System.out.println("Neplatná pozice, zadej ji prosím znovu.");
                    platnostZadani = false;
                }
                rada ++;
            } while (!platnostZadani && vitez.equals("?"));

            //vypsani hraciho pole + vyhodnoceni radku 
            for (int k = 0; k < piskvorky[0].length - 1; k++) {
                for (int l = 0; l < piskvorky.length; l++) {
                    System.out.print(piskvorky[k][l]);
                    radek += piskvorky[k][l];
                }
                System.out.println();
                if (radek.contains(vitezO)) {
                    vitez = vitezO;
                }
            }
            //vyhodnoceni sloupcu
            for (int i = 0; i < piskvorky.length; i++) {
                for (int j = 0; j < piskvorky[0].length; j++) {
                    sloupec += piskvorky[j][i];
                }
                if (sloupec.contains(vitezO)) {
                    vitez = vitezO;
                } else if (sloupec.contains(vitezX)) {
                    vitez = vitezX;
                }
            }
            //vyhodnoceni leve diagonaly
            for (int i = 0; i < piskvorky[0].length; i++) {
                for (int j = 0; j < piskvorky.length - i; j++) {
                    levaDiagonala1 += piskvorky[j][j + i];
                    levaDiagonala2 += piskvorky[j + i][j];
                    if (levaDiagonala1.contains(vitezO) || levaDiagonala2.contains(vitezO)) {
                        vitez = vitezO;
                    } else if (levaDiagonala1.contains(vitezX) || levaDiagonala2.contains(vitezX)) {
                        vitez = vitezX;
                    }
                }
            }
            //vyhodnoceni prave diagonaly
            for (int i = piskvorky[0].length - 1; i > 0; i--) {
                for (int j = 0; (j + Math.abs(piskvorky.length - 1 - i)) < piskvorky.length; j++) {
                    pravaDiagonala1 += piskvorky[piskvorky.length - 1 - j][j + (Math.abs(piskvorky.length - 1 - i))];
                    pravaDiagonala2 += piskvorky[j][Math.abs(j - i)];
                    if (pravaDiagonala1.contains(vitezO) || pravaDiagonala2.contains(vitezO)) {
                        vitez = vitezO;
                    }
                    if (pravaDiagonala1.contains(vitezX) || pravaDiagonala2.contains(vitezX)) {
                        vitez = vitezX;
                    }
                }
            }
            //zjisteni zaplneni pole
            for (int k = 1; k < piskvorky[0].length - 1; k++) {
                for (int l = 2; l < piskvorky.length; l++) {
                    remiza += piskvorky[k][l];
                }
                if (!remiza.contains(" ")) {
                    vitez = "remiza";
                }
            }
            if (vitez.equals(vitezO) || (vitez.equals("remiza"))) {
                break;
            } else if (vitez.equals(vitezX) || (vitez.equals("remiza"))) {
                break;
            }
        } while (vitez.equals("?"));
        if (vitez.equals(vitezO)) {
            System.out.println("Vyhrál hráč s kolečky");
        } else if (vitez.equals(vitezX)) {
            System.out.println("Vyhrál hráč s křížky");
        } else if (vitez.equals("remiza")) {
            System.out.println("Remíza.");
        }
    }
}
