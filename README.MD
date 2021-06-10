# Modular POC

## Description

We have modular monolith. Each module has assigned group (POC: we have group A and B, example command from module
B (`B1Command`) required action in module A `A1Command`).

POC checks possibility to split group of modules to separated app which communicate to different modules by REST.

## Switching app mode

Enabling direct communication between modules in different group required only one change - activating profile.

When you want to run app with modules A, you should add profile `A`.

When you want to run app with modules B, you should add profile `B`.
