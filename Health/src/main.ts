import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module'; // Replace with your AppModule import
import '@angular/compiler'; // Import the Angular compiler

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));