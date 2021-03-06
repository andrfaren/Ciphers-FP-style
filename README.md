# Ciphers-FP-style
Ciphers re-written using Java 8+ language features and functional programming.

Supports the following ciphers:
  - Caesar (shift)
  - Unicode

## Description
The application is run through the command line (Main method) and accepts the following commands and options:

- \- mode [enc | dec] specifies either encrpytion nor decryption
- \- in [filename] specifies the input filename
- \- out [filename] specifies the outputfilename
- \- key [i] specifies the degree of shift (positive values only)
- \- alg [unicode | shift] specifies the algorithm 
- \- data [s] specifies a string to be encrypted or decrypted (if no file input/output is needed)

### Example 1:

```bash
java Main -mode enc -in plaintext.txt -out cipher-text.txt -key 5 -alg unicode
```

### Example 2:
```bash
java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
```
