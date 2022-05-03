# Ciphers-FP-style
Ciphers re-written using Java 8+ language features and the functional programming style.

## Description
The application is run through the command line (Main method) and accepts the following commands and options:

\- mode [enc | dec] specifies either encrpytion nor decryption
\- in [filename] specifies the input filename
\- out [filename] specifies the outputfilename
\- key [unsigned integer] specifies the degree of shift
\- alg [unicode | shift] specifies the algorithm 

### Example 1:

```bash
java Main -mode enc -in plaintext.txt -out cipher-text.txt -key 5 -alg unicode
```

### Example 2:
```bash
java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
```
