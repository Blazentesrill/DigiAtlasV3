# 📱 DigiAtlas — Digivice Evolution Guide

DigiAtlas is an Android application that provides a comprehensive evolution reference for classic Digimon virtual pet devices. The app allows users to select a Digivice, choose an egg/version, and explore detailed Digimon evolution trees with clear requirements and branching paths.

This project is built as a data-driven reference companion to help players plan Digimon evolutions without trial-and-error.

---

##  Features

- Device-based Digivice selection (DM20, DMX, and more)
- Egg and version selection per device
- Branching evolution trees with OR-based paths
- Clear evolution requirements (care mistakes, training, battles, areas)
- Smooth navigation between Digimon stages
- Offline support (no internet required)
- Clean UI inspired by classic Digimon aesthetics

---

## Tech Stack

- **Language:** Java  
- **Platform:** Android (Android Studio)  
- **UI:** XML layouts, Material Design components  
- **Data:** JSON-based evolution data  
- **Parsing:** Gson  
- **Architecture:** Activity-based navigation with a repository pattern  

---

## App Flow
Title Screen

↓

Device Select

↓

Egg Select

↓

Digimon List

↓

Digimon Detail (Evolution Paths)


Each Digivice version is loaded dynamically based on user selection.

---

## Project Structure (Simplified)
app/
├─ src/main/
│ ├─ java/com/declan/digivice/digiatlasv3/
│ │ ├─ Activity files
│ │ ├─ Adapters
│ │ ├─ Models
│ │ └─ Repository
│ ├─ assets/
│ │ ├─ dm_ver1.json
│ │ ├─ dm_ver2.json
│ │ └─ dmx_verA.json
│ └─ res/
│ ├─ layout/
│ ├─ drawable/
│ └─ values/


##  Data Design

Digimon evolution data is stored in structured JSON files:

```json
{
  "id": "agumon",
  "name": "Agumon",
  "stage": "Rookie",
  "evolvesFrom": ["koromon"],
  "evolvesTo": [
    {
      "to": "greymon",
      "requirements": ["0-2 Care Mistakes", "16+ Training"]
    }
  ]
}
