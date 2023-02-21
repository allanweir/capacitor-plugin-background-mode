import { WebPlugin } from '@capacitor/core';

import type { BackgroundModePlugin, ISettings } from './definitions';

export class BackgroundModeWeb
  extends WebPlugin
  implements BackgroundModePlugin {
    async enable(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async disable(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async getSettings(): Promise<{settings: ISettings}>{
      throw this.unimplemented('Not implemented on web.');
    }
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async setSettings(_settings: Partial<ISettings>): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async checkForegroundPermission(): Promise<{enabled: boolean}>{
      throw this.unimplemented('Not implemented on web.');
    }
    async requestForegroundPermission(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async isIgnoringBatteryOptimizations(): Promise<{isIgnoring: boolean}>{
      throw this.unimplemented('Not implemented on web.');
    }
    async disableBatteryOptimizations(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async enableWebViewOptimizations(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async disableWebViewOptimizations(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async moveToBackground(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async moveToForeground(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async isScreenOff(): Promise<{isScreenOff: boolean}>{
      throw this.unimplemented('Not implemented on web.');
    }
    async isEnabled(): Promise<{enabled: boolean}>{
      throw this.unimplemented('Not implemented on web.');
    }
    async isActive(): Promise<{activated: boolean}>{
      throw this.unimplemented('Not implemented on web.');
    }
    async wakeUp(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async unlock(): Promise<void>{
      throw this.unimplemented('Not implemented on web.');
    }
    async sdkVersion(): Promise<{version: number}> {
      throw this.unimplemented('Not implemented on web.');
    }
}
