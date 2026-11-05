import { CDManager } from './CDManager';

const manager = new CDManager('cds.json');

manager.addCD('1', 'The Dark Side of the Moon', 'Pink Floyd', 1973);
manager.addCD('2', 'Thriller', 'Michael Jackson', 1982);

console.log("¡CDManager inicializado y discos agregados correctamente!");