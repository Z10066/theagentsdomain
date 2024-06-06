export interface IFileConfiguration {
  id?: number;
  name?: string;
  description?: string | null;
  path?: string;
  types?: string;
}

export class FileConfiguration implements IFileConfiguration {
  constructor(public id?: number, public name?: string, public description?: string | null, public path?: string, public types?: string) {}
}

export function getFileConfigurationIdentifier(fileConfiguration: IFileConfiguration): number | undefined {
  return fileConfiguration.id;
}
