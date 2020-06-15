import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pascalCaseFilter'
})
export class PascalCaseFilterPipe implements PipeTransform {

  transform(word: string): string {
    return word[0].toUpperCase() + word.slice(1).toLowerCase();
  }

}
