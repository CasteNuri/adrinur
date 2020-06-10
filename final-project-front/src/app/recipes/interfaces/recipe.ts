export interface Recipe {
  codRec?: number;
  title: string;
  image: string;
  ingredients?: any;
  quantities: string;
  time: number;
  difficulty: string;
  description: string;
  type: string;
  rating: number;
  favourite: boolean;
  user?: any;
}
