package sus.amogus.pkmn.models.web.processes;

import sus.amogus.pkmn.models.Card;

import java.io.*;

public class CardExport
{
    public static void ExportToFile(Card card)
    {
        String fileName = card.getName() + ".crd";
        FileOutputStream myFile = null;
        try {
            myFile = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(myFile);
            out.writeObject(card);
            System.out.println("\u001b[38;5;201mС\u001b[38;5;208mе\u001b[38;5;212mр\u001b[38;5;215mи\u001b[38;5;218mз\u001b[38;5;221mа\u001b[38;5;224mц\u001b[38;5;227mи\u001b[38;5;230mя\u001b[38;5;0m \u001b[38;5;201mв\u001b[38;5;208mы\u001b[38;5;212mп\u001b[38;5;215mо\u001b[38;5;218mл\u001b[38;5;221mн\u001b[38;5;224mе\u001b[38;5;227mн\u001b[38;5;230mа");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}