# Modular POC

## Description

We have modular monolith. Each module has assigned group (POC: we have group A and B, example command from module
B (`B1Command`) required action in module A `A1Command`).

POC checks possibility to split group of modules to separated app which communicate to different modules by REST.

## App profile mode

### A,B (all activated)

All modules work - direct communication between modules.

### only one module A or B

App activate only one module - when app must run action in different module, it uses rest communication (you must run
app twice, app1 works with profile A, app2 works with profile B)
