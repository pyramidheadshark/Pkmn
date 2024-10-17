package ru.mirea.pkmn.smirnovnd;

import ru.mirea.pkmn.Card;

public class PkmnApplication
{
    public static void main(String[] args)
    {
        CardImport cardImport = new CardImport();
        Card myCard = cardImport.readFromFile("src/main/resources/Blipbug.txt");
        System.out.printf("\u001b[38;5;186m\nНачало задачи:\u001b[38;5;0m\n");
        //System.out.printf(myCard.toString());

        CardExport.ExportToFile(myCard);
        myCard = cardImport.importFromFile("Dottler.crd");
        System.out.printf(myCard.toString());
    }
}