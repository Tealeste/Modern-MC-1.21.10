## 现代化UI - 自用 Fork
> 自用分支，将持续维护到 BloCamLimb 分支更新1.21.10后。([English version](#modern-ui-for-minecraft))

### 项目简介
ModernUI-MC 在 Minecraft 客户端内嵌了 [ModernUI 框架](https://github.com/Tealeste/Modern-MC-1.21.10)，让基于 ModernUI 的桌面应用/模块能够无缝在游戏内运行，同时为原版界面注入现代化文字渲染、动画与大量实用工具。玩家只需放入一个 jar，即可获得完整运行时、字体与扩展，开发者也能复用同一套 API。

发布渠道： [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modern-ui) ｜ [Modrinth](https://modrinth.com/mod/modern-ui)。问题反馈请前往 [ModernUI Issue Tracker](https://github.com/Tealeste/Modern-MC-1.21.10/issues)，也欢迎加入 [Discord](https://discord.gg/kmyGKt2) 交流。

### 快速上手
1. 从 CurseForge/Modrinth 下载匹配的 ModernUI-MC 版本。
2. 将 jar 放入 `mods/` 目录；ModernUI 核心、字体与扩展已经全部打包。
3. 启动游戏后在 Center UI 中调整模糊、字体或提示框等设置。
4. 若需要第三方 ModernUI 应用，将其放入 `config/ModernUI/apps/` 即可出现在游戏内启动器。
5. 通过 `modernui_mc_skipGLPromotion` 等 JVM 引导参数可快速定位渲染类问题。

### 主要特性
- **ModernUI 运行时**：Minecraft 中直接启动 ModernUI 应用，或混合 ModernUI/原版 UI；内置 UI 管理器、动画系统与调试工具。
- **现代文字引擎**：实时预览/热重载 TTF/OTF，改进字体回退、SDF 渲染、Unicode 分段与 Emoji（含 16.0 与常见短代码）。
- **画面与体验增强**：可调模糊/渐变背景、平滑滚动、可调 GUI 缩放、OptiFine 风格缩放、单人暂停、焦点音量/帧率衰减、自定 “Ding” 提示音、全新圆角提示框等。
- **开发向 API**：Forge / NeoForge / Fabric 三端统一 API、Modern Text Engine 钩子、配置抽象与引导参数（如 `modernui_mc_skipGLPromotion`、Arc3D 相关开关）。
- **资源热重载**：字体、图标、音频与配置均支持即时生效，方便美术/设计联调。

### 版本支持矩阵

| Minecraft | 加载器 | ModernUI-MC | 说明 |
| --- | --- | --- | --- |
| 1.21.10 | NeoForge / Forge / Fabric | 3.12.0.8 | 修复文本框格式化器的顺序，确保现代渲染始终在其他格式化器之后生效。 |
| 1.21.6 – 1.21.8 | NeoForge / Forge / Fabric | 3.12.0.5 | 更新 Center UI、字体与模糊控制，新增 Arc3D 引导参数。
| 1.21.4 | NeoForge / Forge / Fabric | 3.12.0.3 | 与 3.12.0.5 基本一致，去除 1.21.8 特定改动。
| 1.21 – 1.21.1 | NeoForge / Forge / Fabric | 3.12.0.2 | 焕新文字引擎，支持无阴影渲染与乱码修复。
| 1.20 – 1.20.1 | NeoForge / Forge / Fabric | 3.12.0.1 | 1.20.x 最后一个主线版本。

### 安装与设置
1. 安装对应版本的 NeoForge / Forge / Fabric 加载器，并确认 Minecraft 与 ModernUI-MC 的版本矩阵匹配。
2. 从 [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modern-ui/files) 或 [Modrinth](https://modrinth.com/mod/modern-ui/versions) 下载 jar。
3. 将 jar 放入 `mods/` 目录；无需额外字体或资源包，ModernUI 运行时会自动加载。
4. 启动游戏后在 Center UI 中即时调整模糊、字体、提示框等设置，并根据需要开启单人暂停、平滑滚动等功能。
5. ModernUI 应用/脚本可放入 `config/ModernUI`，也可通过 Modrinth/Curse 上的附加组件获取。

### 常见问题（FAQ）
- **ModernUI-MC 与 ModernUI 框架是什么关系？** ModernUI 框架（独立仓库）提供 UI 运行时与文字引擎；ModernUI-MC 负责把它打包进 Forge/NeoForge/Fabric，并实现加载器特有的桥接层。开发或调试运行时时，可在仓库根目录旁克隆 `../ModernUI` 并通过 `includeBuild` 自动联动。
- **可以在整合包或服务器端使用吗？** ModernUI-MC 只在客户端生效，也不会影响服务端。整合包内建议保留 `config/ModernUI`，以便分发字体或预设。若需要关闭全部改动，可在 Mod 菜单中禁用特定 ModernUI 扩展。
- **如何排查字体或渲染问题？** 启动参数中加入 `-Dmodernui_mc_skipGLPromotion=true` 或 `-Darc3d_context_allowGLSPIRV=false` 可逐步缩小范围，Center UI 也自带日志与调试面板。

### 构建与开发
ModernUI-MC 需要 JDK 21 与 Gradle 8.14+。为了同时修改核心运行时与加载器桥接层，建议把核心仓库克隆到同级目录并让 `includeBuild` 自动工作：

```bash
git clone https://github.com/Tealeste/Modern-MC-1.21.10-MC.git
cd ModernUI-MC
git clone https://github.com/Tealeste/Modern-MC-1.21.10 ../ModernUI
./gradlew build # 构建全部子模块
```

- 常用任务：`./gradlew :fabric:build`、`:forge:build`、`:neoforge:build`、`:common:jar`。所有产物都位于各自 `build/libs/`。
- 开发环境需使用 IzzelAliz Maven，因此本地坐标可能与 CurseForge/Modrinth 发布版不同。
- Fabric / Loom 与 NeoForge / ModDevGradle 示例仍保留在英文部分，可直接复制粘贴。
- 调试渲染问题时可通过 `modernui_mc_skipGLPromotion`、`arc3d_context_useStagingBuffers`、`arc3d_context_allowGLSPIRV` 等引导参数快速定位。

### 支持 & 反馈
- Bug / 功能需求：<https://github.com/Tealeste/Modern-MC-1.21.10/issues>
- 玩家/开发者讨论：<https://discord.gg/kmyGKt2>
- 提交 PR：Minecraft 端改动提至本仓库，核心框架改动请前往 ModernUI 仓库。

### 许可证与致谢
- 代码遵循 [LGPL-3.0-or-later](https://www.gnu.org/licenses/lgpl-3.0.en.html)。
- 内置字体/资源包含 Source Han Sans、JetBrains Mono、自研 Emoji（基于 Google Noto Color Emoji）与 “Still Alive” 自适配版本，具体请见 `assets/`。

# Modern UI for Minecraft
[中文版本](#现代化ui)
[![CurseForge](http://cf.way2muchnoise.eu/full_352491_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/modern-ui)
[![Modrinth](https://img.shields.io/modrinth/dt/3sjzyvgr?label=Modrinth&logo=modrinth)](https://modrinth.com/mod/modern-ui)

> A full ModernUI runtime, a modern text engine, and a suite of QoL upgrades packaged as a single Minecraft mod for Forge, NeoForge, and Fabric.

## Overview
Modern UI for Minecraft (ModernUI-MC) bootstraps the [ModernUI framework](https://github.com/Tealeste/Modern-MC-1.21.10) in the Minecraft client. Mods built with ModernUI can run natively inside the game, while vanilla screens automatically gain access to a rich text system, higher fidelity rendering, and a number of productivity tweaks. The mod ships with its own runtime so players only have to drop one jar into their `mods` folder.

Releases are distributed on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modern-ui) and [Modrinth](https://modrinth.com/mod/modern-ui). Issues and feature requests live in the core [ModernUI tracker](https://github.com/Tealeste/Modern-MC-1.21.10/issues), and our community hangs out on [Discord](https://discord.gg/kmyGKt2).

## Quick start
1. Grab the release that matches your Minecraft + loader version.
2. Drop the jar into `mods/`—it already bundles ModernUI Core, Markflow/Markdown, fonts, and runtime assets.
3. Launch Minecraft and open the Center UI to toggle blur, typography, tooltip layouts, zoom helpers, and more.
4. Place ModernUI desktop apps into `config/ModernUI/apps/` to have them show up in-game.
5. JVM flags such as `-Dmodernui_mc_skipGLPromotion=true` and `-Darc3d_context_allowGLSPIRV=false` help diagnose rendering issues without rebuilding.

## Highlights
### ModernUI runtime inside Minecraft
- Launch complete ModernUI applications within Minecraft without code changes.
- Provide a bridge layer so mods can mix ModernUI-powered screens with enhanced vanilla GUIs.
- Ship a UI manager, scene graph, animation system, and tooling shared with desktop ModernUI apps.

### Modern text & layout engine
- Real-time preview/reload of TrueType & OpenType fonts with robust fallback logic.
- Anti-aliased rendering with FreeType hinting, SDF glyphs (2D & 3D), and batch pipelines tuned for the client.
- Accurate glyph metrics, Unicode line-breaking, Bidi heuristics, configurable shadows, and emoji (Unicode 16.0 + shortcodes for Discord/Slack/JoyPixels, etc.).
- Highly optimized atlases and rectangle packing that cut allocations and GC pressure in vanilla, Forge, and Fabric contexts.

### Quality of life & presentation upgrades
- Blur, radial blur, grayscale, and background color controls with smooth fade-in transitions.
- Additional window modes (borderless fullscreen, maximized), GUI scale slider hints, zoom-on-demand (OptiFine-style "C" zoom), and smooth scrolling for vanilla and Forge lists.
- Context-aware automation such as pausing singleplayer when inventories are open, focus-based FPS/audio fading, optional "Ding" when the menu is ready, tooltip overhauls (rounded borders, RTL, gradients), and undo/redo in text inputs.
- Built-in music player with spectrum visualization and seeking support.

### Developer friendly features
- Loader-specific APIs for Forge, NeoForge, and Fabric including menu implementations and helper screens.
- Modern Text Engine hooks expose better layout primitives even for vanilla-styled components.
- Config abstractions, bootstrap toggles, and platform services to keep complex UI mods maintainable.

## Editions & support matrix
The current release line is **3.12.0.x** powered by ModernUI **3.12.0**. Pick the artifact that matches your loader and Minecraft version:

| Minecraft | Loader(s) | ModernUI-MC Version | Notes |
| --- | --- | --- | --- |
| 1.21.10 | NeoForge / Forge / Fabric | 3.12.0.8 | Fixes text formatter ordering so ModernUI's rendering runs after other formatters. |
| 1.21.6 – 1.21.8 | NeoForge / Forge / Fabric | 3.12.0.5 | Includes the reworked Center UI, new blur defaults, updated fonts, and Arc3D bootstrap toggles. |
| 1.21.4 | NeoForge / Forge / Fabric | 3.12.0.3 | Feature parity with 3.12.0.5 minus the 1.21.8-specific hooks. |
| 1.21 – 1.21.1 | NeoForge / Forge / Fabric | 3.12.0.2 | Uses the refreshed text engine, no-shadow rendering, and obfuscated text fixes. |
| 1.20 – 1.20.1 | NeoForge / Forge / Fabric | 3.12.0.1 | Final major update for 1.20.x packs. |

Older history is kept intact in [`changelogs.md`](./changelogs.md). If you maintain a modpack, stick to the version line that matches your Minecraft target; the jars are not cross-compatible across those ranges.

## Installation
1. Install your preferred loader (NeoForge, Forge, or Fabric) for a supported Minecraft version.
2. Download the matching ModernUI-MC release from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modern-ui/files) or [Modrinth](https://modrinth.com/mod/modern-ui/versions).
3. Drop the jar into your profile's `mods` directory. The package already bundles the ModernUI runtime and extensions, so no extra dependencies are required for players.
4. (Optional) Configure ModernUI from the in-game Center screen. Tweak blur strength, font packs, tooltips, and more without restarting the client.

When ModernUI-MC detects OptiFine, Sodium/Rubidium, or Iris/Oculus, it adjusts compatible rendering paths automatically. Manual overrides are available in `config/modernui_mc.toml` (Forge/NeoForge) or the Fabric config directory.

## Build & development
ModernUI-MC shares code with the core ModernUI repository. Clone both repos side-by-side if you plan to work on loader glue and the runtime at the same time:

```bash
# prerequisites: JDK 21, Git, and enough disk space for the game assets.
git clone https://github.com/Tealeste/Modern-MC-1.21.10-MC.git
cd ModernUI-MC
git clone https://github.com/Tealeste/Modern-MC-1.21.10 ../ModernUI
./gradlew build
```

The resulting jars live in `ModernUI-{Loader}/build/libs/` and already include the framework, Markflow/Markdown parser, and runtime assets. Common workflows:

- `./gradlew :fabric:build`, `:forge:build`, or `:neoforge:build` to build one loader at a time.
- `./gradlew :ModernUI-Fabric:runClient` (or the Forge/NeoForge equivalent) for smoke testing.
- Set `-PdevEnv` to wire the included ModernUI composite build when editing both repos.

### Development tips
- Add `icyllis.modernui` dependencies as a `library` configuration inside your own Gradle project; see the snippets near the bottom of this README for ForgeGradle 6+ and Loom setups.
- Use `modernui_mc_skipGLPromotion`, `arc3d_context_allowGLSPIRV`, and related bootstrap properties when debugging custom renderers.
- Platform hooks live in `icyllis.modernui.mc.<loader>` packages; cross-loader behavior should go into the `common/` module.

### Repository configuration
Development builds must point to the ModernUI Maven because the versions we publish there differ from CurseForge/Modrinth jars.

```groovy
repositories {
    maven {
        name 'IzzelAliz Maven'
        url = uri('https://maven.izzel.io/releases/')
    }
    // If you are on Fabric, uncomment this helper repo for Forge Config API Port.
    /*maven {
        url = uri('https://raw.githubusercontent.com/Fuzss/modresources/main/maven/')
        content {
            includeGroup 'fuzs.forgeconfigapiport'
        }
    }*/
}
```

### Fabric / Architectury Loom example

```groovy
dependencies {
    // Uncomment when you need Forge Config API Port on Fabric and match the FCAPI version.
    // modApi "fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${fcapi_version}"
    implementation "icyllis.modernui:ModernUI-Core:${modernui_version}"
    // Markflow (>=3.12.0) or Markdown (<=3.11.1) should be bundled alongside the core runtime.
    implementation "icyllis.modernui:ModernUI-Markflow:${modernui_version}"
    modImplementation "icyllis.modernui:ModernUI-Fabric:${minecraft_version}-${modernui_version}.+"
}
```

### NeoForge / ModDevGradle example

```groovy
dependencies {
    implementation "icyllis.modernui:ModernUI-NeoForge:${minecraft_version}-${modernui_version}.+"
    additionalRuntimeClasspath(compileOnly("icyllis.modernui:ModernUI-Core:${modernui_version}")) {
        exclude group: "org.slf4j", module: "slf4j-api"
        exclude group: "org.apache.logging.log4j", module: "log4j-core"
        exclude group: "org.apache.logging.log4j", module: "log4j-api"
        exclude group: "com.google.code.findbugs", module: "jsr305"
        exclude group: "org.jetbrains", module: "annotations"
        exclude group: "com.ibm.icu", module: "icu4j"
        exclude group: "it.unimi.dsi", module: "fastutil"
    }
    additionalRuntimeClasspath(compileOnly("icyllis.modernui:ModernUI-Markflow:${modernui_version}")) {
        exclude group: "org.slf4j", module: "slf4j-api"
        exclude group: "org.apache.logging.log4j", module: "log4j-core"
        exclude group: "org.apache.logging.log4j", module: "log4j-api"
        exclude group: "com.google.code.findbugs", module: "jsr305"
        exclude group: "org.jetbrains", module: "annotations"
        exclude group: "com.ibm.icu", module: "icu4j"
        exclude group: "it.unimi.dsi", module: "fastutil"
    }
}
```

## FAQ
- **What is the difference between ModernUI and ModernUI-MC?** ModernUI is the standalone UI/runtime project; ModernUI-MC embeds it in Minecraft and implements the loader glue. When you keep the `ModernUI` repository next to this one, Gradle's `includeBuild` wiring lets you test runtime changes without publishing artifacts.
- **Can I bundle this in a modpack or run it on a server?** ModernUI-MC is client-side only but safe to ship in packs. Keep `config/ModernUI` and the bundled fonts so players inherit consistent presets. Dedicated servers never load the mod.
- **Where do ModernUI desktop apps go?** Drop `.muiapp`/`.zip` packages inside `config/ModernUI/apps/`. They will show up in the in-game launcher, inherit the same fonts, and can call back into Minecraft via the exposed bridge APIs.

## Support & contributing
- **Bug reports & feature requests:** [ModernUI issue tracker](https://github.com/Tealeste/Modern-MC-1.21.10/issues).
- **Discussions & help:** [Discord server](https://discord.gg/kmyGKt2).
- **Pull requests:** target this repository for loader-specific changes; core UI/runtime work should go to the ModernUI repo.

Please include logs, loader versions, and repro steps, especially when dealing with rendering or text-layout quirks.

## License & credits
- Code is licensed under [LGPL-3.0-or-later](https://www.gnu.org/licenses/lgpl-3.0.en.html). Copyright © 2019‑2025 BloCamLimb & contributors.
- Bundled assets include [Source Han Sans](https://github.com/adobe-fonts/source-han-sans) (OFL-1.1), [JetBrains Mono](https://www.jetbrains.com/lp/mono/) (OFL-1.1), custom emoji sheets derived from Google Noto Color Emoji, and Valve's "Still Alive" cover (see `assets/` for details).

## Screenshots & media
The UI evolves quickly—visit the mod pages for the newest gallery. A few representative shots are kept below for archival purposes:

- Center screen (March 2024): ![2024-03-30_16.17.11.png](https://cdn.modrinth.com/data/3sjzyvGR/images/2571f7372b1f9bbb116c118f29a93255338f4e41.png)
- Enhanced tooltips: ![new tooltip.png](https://s2.loli.net/2024/03/30/VhyoFPAD2Js1HWO.png)
- Markdown renderer: ![markdown](https://cdn.modrinth.com/data/3sjzyvGR/images/989a77ba61c62ff580a30dcf158e391080b949bd.png)
- Audio visualization: ![new2](https://i.loli.net/2021/09/24/TJjyzd6oOf5pPcq.png)
