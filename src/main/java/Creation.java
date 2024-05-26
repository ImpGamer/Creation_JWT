import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.ZonedDateTime;
import java.util.Date;

public class Creation {
    public static void main(String[] args) {
        //Instancia de la fecha y hora actual
        ZonedDateTime zdt = ZonedDateTime.now();

        //Fecha formateada a UNIX (issuedAt)
        long iat = zdt.toInstant().getEpochSecond();

        //Creacion de fecha de expiracion con formato (expiresAt)
        long expiraEn = iat+3600; //Se le agrego 1 hora a la fecha actual para su expiracion

        //Datos del usuario
        String user = "David Alfredo";
        String email = "David990@gmail.com";

        /*Frase o archivo unico para validacion de JWT, esto para agregarle un atributo extra
        * constante, es decir que debe venir implementado en el JWT. En este caso utilizaremos
        * una frase "String" pero lo recomandable es usar un cifrado con librerias (OpenSSL)*/
        final String frase = "this-is-a-really-long-phrase-for-my-JWT";

        //Creacion del objeto que almacenara todos los datos del usuario
        // (usuario,email,fechaCreacion,fechaDeExpiracion)
        UserData userData = new UserData(user,email,iat,expiraEn);
        //Creacion del JWT o Token
        String token = "";
        try {
            //Encriptacion de la clave o frase
            Algorithm codificadoEn = Algorithm.HMAC256(frase);
            //Creacion del token
            token = JWT.create()
                    .withIssuer(userData.getUser())
                    //Como los objeto tipo Date esperan milisegundos y el fomato UNIX esta
                    //En segundos, se multiplica por 1000
                    .withIssuedAt(new Date(userData.getIat()*1000))
                    .withExpiresAt(new Date(userData.getExpireAt()*1000))
                    //Frase o archivo ya codificado como firma del token
                    .sign(codificadoEn);
            System.out.println("Mi token: "+token);
        }catch (JWTCreationException e) {
            System.out.println("No se pudo crear el token: "+e.getMessage());
        }

        //Validacion del Token
        try {
            Algorithm algorithm = Algorithm.HMAC256(frase);
            //Creacion del objeto verificador
            JWTVerifier verificador = JWT.require(algorithm)
                    .build();
            //El objeto verifica el token
            verificador.verify(token);
            //Imprime si es valido o no
            System.out.println("El token es valido");
        }catch (JWTVerificationException e) {
            System.out.println("El token no es valido "+e.getMessage());
        }
    }

}