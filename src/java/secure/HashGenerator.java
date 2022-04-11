package secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * <b>Java SE 8 available MessageDigest algorithms:</b><br>
 * The algorithm names in this section can be specified when generating an
 * instance of MessageDigest.
 * <li>MD2: The MD2 message digest algorithm as defined in
 * <a href="https://tools.ietf.org/html/rfc1319">RFC 1319.</a></li>
 * <li>MD5: The MD5 message digest algorithm as defined in
 * <a href="https://tools.ietf.org/html/rfc1321">RFC 1321.</a></li>
 * <p>
 * Below is the list of Hash algorithms defined in the
 * <a href="https://csrc.nist.gov/publications/detail/fips/180/4/final">FIPS PUB
 * 180-4.</a></p><br>
 * <li>SHA-1</li>
 * <li>SHA-224</li>
 * <li>SHA-256</li>
 * <li>SHA-384</li>
 * <li>SHA-512</li>
 * <li>SHA-512/224</li>
 * <li>SHA-512/256</li>
 * <p>
 * Secure hash algorithms - SHA-1, SHA-224, SHA-256, SHA-384, SHA-512 - for
 * computing a condensed representation of electronic data (message). When a
 * message of any length less than 264 bits (for SHA-1, SHA-224, and SHA-256) or
 * less than 2128 (for SHA-384 and SHA-512) is input to a hash algorithm, the
 * result is an output called a message digest. A message digest ranges in
 * length from 160 to 512 bits, depending on the algorithm.</p>
 * <p>
 * <b>SHA-256</b> produces a 256-bit (32 bytes) hash value. It's usually
 * represented as a hexadecimal number of <b>64 digits</b>.<br>
 * SHA-512 produces 512-bit (64 bytes) hash value. It's usually represented as a
 * hexadecimal number of <b>128 digits</b>.<br></p>
 *
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class HashGenerator {

    //SHA-256 vs SHA-512
    /*
    In practical terms, SHA-256 is just as secure as SHA-384 or SHA-512. We can't produce collisions 
    in any of them with current or foreseeable technology, so the security you get is identical.
    From a non-security perspective, the reasons to choose SHA-256 over the longer digests are more easily apparent: 
    it's smaller, requiring less bandwidth to store and transmit, less memory and in many cases less processing 
    power to compute. (There are cases where SHA-512 is faster and more efficient.)
    Third, there are likely compatibility issues. Since virtually no one uses certs with SHA-384 or SHA-512, 
    you're far more likely to run into systems that don't understand them. There are probably fewer issues now 
    than in the past, but again, you're buying yourself risk for no gain.
    So, at the present time, there are no clear advantages to choosing SHA-384 or SHA-512, 
    but there are obvious disadvantages. This is why SHA-256 is the universal choice for modern certs for websites.
    
    The only real advantage that SHA-512 might have over SHA-256 is collision resistance, 
    a term that in cryptography has a very narrow meaning. SHA-256 claims 128-bit collision resistance, 
    SHA-512 claims 256-bit. If or when a practical quantum computer is built, we might need 
    the 256-bit collision resistance.
    Since SSL certificates typically have expiration dates in a relatively short term, it's just fine 
    to get a SHA-256 certificate today, because it'll expire before a practical quantum computer is built 
    (if that ever happens).
    Apart from that:
    SHA-256 outputs are shorter, which saves bandwidth.
    Different hardware favors different functions. SHA-512 is generally faster on 64-bit processors, 
    SHA-256 faster on 32-bit processors. (Try the command openssl speed sha256 sha512 on your computer.)
    SHA-512/256 sits right in between the two functions—the output size and security level of SHA-256 
    with the performance of SHA-512—but almost no systems use it so far.
     */
    private final String HASH_ALGORITHM = "SHA-256";
    private MessageDigest messageDigest;
    private byte[] inputDataBytes;
    private byte[] processedBytes;
    private String hashValue;

    public String toSHA2(String inputData) {

        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException nsaExc) {
            throw new RuntimeException("Specified algorithm is not supported\n", nsaExc.getCause());
        }

        inputDataBytes = inputData.getBytes();
        messageDigest.update(inputDataBytes);
        processedBytes = messageDigest.digest(inputDataBytes);

        try {
            hashValue = DatatypeConverter.printHexBinary(processedBytes).toLowerCase();
        } catch (IllegalArgumentException iaExc) {
            throw new RuntimeException("Byte array is null\n", iaExc.getCause());
        }

        return hashValue;

    }//toSHA256() END

}
