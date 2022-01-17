package moyeomoyeo.jwt;

public interface JwtProperties {
    String SECRET = "dGhpcy1pcy1tb3llb21veWVvLXByb2plY3QtaS1hbS1rYW5naGVvbi1kby15b3VyLWJlc3Q=";
    //int EXPIRATION_TIME = 60000*10 ; //10분
    int EXPIRATION_TIME = 60000*100 ;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
