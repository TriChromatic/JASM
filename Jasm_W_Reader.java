package jasm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author trichromatic
 */
public class Jasm {

    /**
     * @param args the command line arguments
     */
    Vector<StkBte> stack = new Vector();

    public static void main( String[] args ) {
        Jasm jasm = new Jasm();
        String fileName;
        String txt = ".end";
        fileName = args[0];
        try {
            BufferedReader br = new BufferedReader( new FileReader( fileName ) );
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while ( line != null ) {
                    sb.append( line );
                    sb.append( System.lineSeparator() );
                    line = br.readLine();
                }
                txt = sb.toString();
            } finally {
                br.close();
            }
        } catch ( IOException e ) {
            System.out.println( e.toString() );
        }
        jasm.jasm(txt);
    }

    public int jasm( String args ) {
        String[] cmd = args.split( "[.*!-]" ); //Pars args
        String dlm = "[,]"; //Declare delim
        int _rp = 0; //Return point

        /*Find where we have functions and add them to the stack*/
        for ( int i = 0; i < cmd.length; i++ ) {
            if ( cmd[i].startsWith( "@" ) ) {
                let( cmd[i], i );
            }
        }

        /*Go through the commands, _pc is the place count*/
        for ( int _pc = 0; _pc < cmd.length; _pc++ ) {
            Object[] tokens = { 0, 0, 0, 0 }; //Create the array
            System.arraycopy( cmd[_pc].split( dlm ), 0, tokens, 0, cmd[_pc].split( dlm ).length ); //Copy array
            Object a = tokens[1]; //First arg
            Object b = tokens[2]; //Second arg
            Object c = tokens[3]; //Third arg
            String param = tokens[0].toString(); //Parameter
            double aD = 0x0;
            double bD = 0x0;

            try {
                aD = Double.parseDouble( get( a ).toString() );
            } catch ( NumberFormatException e ) {
            }
            try {
                bD = Double.parseDouble( get( b ).toString() );
            } catch ( NumberFormatException e ) {
            }

            switch ( param ) {
                case "let":
                    let( a, b );
                    break;
                case "bug":
                    System.out.println( a );
                    break;
                case "add": {
                    double sum = aD + bD;
                    let( a, sum );
                    break;
                }
                case "div": {
                    double quo = aD / bD;
                    let( a, quo );
                    break;
                }
                case "mod": {
                    double mod = aD % bD;
                    let( a, mod );
                    break;
                }
                case "sub": {
                    double dif = aD - bD;
                    let( a, dif );
                    break;
                }
                case "mut": {
                    double pro = aD * bD;
                    let( a, pro );
                    break;
                }
                case "out":
                    System.out.println( get( a ) );
                    break;
                case "rtn":
                    _pc = _rp;
                    break;
                case "jmp":
                    _rp = _pc; //Set the return point equal to the current place + 1
                    _pc = Integer.parseInt( get( a ).toString() );
                    break;
                case "jig":
                    if ( aD > bD ) {
                        _rp = _pc; //Set the return point equal to the current place + 1
                        _pc = Integer.parseInt( get( c ).toString() );
                    }
                    break;
                case "jil":
                    if ( aD < bD ) {
                        _rp = _pc; //Set the return point equal to the current place + 1
                        _pc = Integer.parseInt( get( c ).toString() );
                    }
                    break;
                case "jie":
                    if ( aD == bD ) {
                        _rp = _pc; //Set the return point equal to the current place + 1
                        _pc = Integer.parseInt( get( c ).toString() );
                    }
                    break;
                case "end":
                    return 0;
            }
        }
        System.out.println( "\nProgram quit with a status of 1!\n" ); //This is excecuted if the program doesnt end with *end
        return 1;
    }

    public Object get( Object o ) {
        for ( StkBte stack1 : stack ) {
            if ( stack1.nm.equals( o ) ) {
                return stack1.val;
            }
        }
        return o;
    }

    public int let( Object n, Object v ) {
        for ( StkBte stack1 : stack ) {
            if ( stack1.nm.equals( n ) ) {
                stack1.val = get( v );
                return 0;
            }
        }
        stack.add( new StkBte( n, v ) );
        return 0;
    }

    class StkBte {

        Object nm;
        Object val;

        StkBte( Object a, Object b ) {
            this.nm = a; //Name
            this.val = b; //Value
        }

    }
}
