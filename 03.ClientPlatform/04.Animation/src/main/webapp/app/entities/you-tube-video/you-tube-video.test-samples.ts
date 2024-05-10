import { IYouTubeVideo, NewYouTubeVideo } from './you-tube-video.model';

export const sampleWithRequiredData: IYouTubeVideo = {
  id: 30789,
  workspace: 'liken furthermore',
  creator: 'surprisingly though regarding',
  theme: 'where',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'pertinent',
  videoTime: 'except indoctrinate finally',
  gender: 'above match meh',
  videolanguage: 'apropos even',
  subtitles: 'mild phew knowingly',
};

export const sampleWithPartialData: IYouTubeVideo = {
  id: 19745,
  workspace: 'principal',
  creator: 'avoid overdo splurge',
  theme: 'answer characterise nest',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'jealously',
  videoTime: 'simple',
  gender: 'whoa cheerful hmph',
  videolanguage: 'wearily',
  subtitles: 'gadzooks deplane the',
};

export const sampleWithFullData: IYouTubeVideo = {
  id: 2791,
  workspace: 'optimistically often',
  creator: 'bah uh-huh',
  theme: 'negative',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'repeatedly tennis',
  videoTime: 'vex yesterday',
  gender: 'palatable blog venture',
  videolanguage: 'properly sabotage',
  subtitles: 'light',
};

export const sampleWithNewData: NewYouTubeVideo = {
  workspace: 'shoestring',
  creator: 'finally',
  theme: 'terrific gosh plus',
  content: '../fake-data/blob/hipster.txt',
  backgroundMusic: 'inasmuch slight',
  videoTime: 'catalogue coleslaw',
  gender: 'unimpressively busily enormously',
  videolanguage: 'jaggedly incidentally',
  subtitles: 'everybody',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
