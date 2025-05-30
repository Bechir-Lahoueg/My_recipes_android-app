# MyRecipes - Android Recipe App

A mobile application for managing and organizing recipes by categories. Built with Java for Android using Firebase Firestore as the backend database.

## Features

- Browse recipes by categories
- Add new recipe categories
- Add recipes to specific categories
- View recipes within each category

## Project Structure

### Main Activities

- `MainActivity.java` - Entry point of the application
- `getstarted.java` - Onboarding screen
- `categories.java` - Displays all recipe categories
- `addcategory.java` - Interface for adding new categories
- `RecipesInCategory.java` - Shows recipes within a selected category
- `addRecipes.java` - Interface for adding new recipes

### Database Structure

The app uses Firebase Firestore with the following structure:
- Collection: `categories`
  - Document: `{category_id}`
    - Fields: `categoryName`
    - Subcollection: `recipes`
      - Document: `{recipe_id}`
        - Fields: `recipeName`, `description`

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Connect to Firebase:
   - The project is already configured with Firebase (see `google-services.json`)
   - Make sure you have access to the Firebase project: `recipeapp-ae53d`

## Dependencies

- Firebase Firestore for database
- AndroidX libraries for UI components

## Requirements

- Android SDK 24+
- Java 8+

## License

This project is licensed under the Apache License 2.0.
