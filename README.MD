# Modular POC

We have modular monolith. Each module has group (POC: we have group A and B).

POC should test possibility to split group of modules to separated app which communicate to different module by REST.

Enabling direct communication to module or between modules in different group required only one change - in active profile.

When you want to run app with modules A, you only add profile `A`.
When you want to run app with modules B, you only add profile `B`.
