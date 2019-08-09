public class Token {

    int type;
    Object value;

    public Token(){}
    
    public Token(int type, Object value){
        this.type = type;
        this.value = value;
    }

    public void tokenBonito(){
        String tipo;
        switch(type){
            case 1:
                tipo = "Entero";
                break;
            default:
                tipo = "NO es entero";
                break;

        }
        System.out.print("<Type: "+ tipo + ", Value: "+ value+">");
    }

}